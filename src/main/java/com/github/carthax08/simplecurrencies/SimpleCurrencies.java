package com.github.carthax08.simplecurrencies;

import com.github.carthax08.simplecurrencies.PapiExpansion.SimpleCurrenciesExpansion;
import com.github.carthax08.simplecurrencies.commands.*;
import com.github.carthax08.simplecurrencies.data.PlayerConfig;
import com.github.carthax08.simplecurrencies.data.PricesConfig;
import com.github.carthax08.simplecurrencies.data.db.mysql.DatabaseHandler;
import com.github.carthax08.simplecurrencies.events.onPlayerJoinEvent;
import com.github.carthax08.simplecurrencies.events.onPlayerLeaveEvent;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public final class SimpleCurrencies extends JavaPlugin {

    private static SimpleCurrencies instance;
    private Random rand;
    private static String version = "3.1.0";

    private static HikariDataSource databaseConnection;

    @Override
    public void onEnable() {

        //API Setup
        instance = this;

        // Plugin startup logic
        rand = new Random();
        getServer().getConsoleSender().sendMessage("[SimpleCurrencies]" + ChatColor.GREEN + "The plugin is initializing... Please wait.");
        //Command Registration
        getCommand("simplecurrencies").setExecutor(new MainCommand(this));
        getCommand("getcurrency").setExecutor(new GetCommand(this));
        getCommand("sendcurrency").setExecutor(new PayCommand());
        getCommand("balance").setExecutor(new BalanceCommand());
        getCommand("sell").setExecutor(new SellCommand());
        getServer().getConsoleSender().sendMessage("[SimpleCurrencies]" + ChatColor.GREEN + "Command registration finished.");

        //Event Registration
        getServer().getPluginManager().registerEvents(new onPlayerJoinEvent(), this);
        getServer().getPluginManager().registerEvents(new onPlayerLeaveEvent(), this);
        getServer().getConsoleSender().sendMessage("[SimpleCurrencies]" + ChatColor.GREEN + "Event registration finished.");

        //Check Version
        if(!checkVersion()){
            getLogger().warning("Simple currencies is outdated! Please update.");
        }

        //Config Registration
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Shop Config Registration
        PricesConfig.setupConfig();
        getServer().getConsoleSender().sendMessage("[SimpleCurrencies]" + ChatColor.GREEN + "Config registration finished.");

        //Placeholders setup
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            new SimpleCurrenciesExpansion(this).register();
            getServer().getConsoleSender().sendMessage("[SimpleCurrencies]" + ChatColor.GREEN + "PlaceholderAPI hook registered.");
        }else{
            getServer().getConsoleSender().sendMessage("[SimpleCurrencies]" + ChatColor.GREEN + "PlaceholderAPI not detected, skipping hook.");
        }

        //Data Storage Initialization
        if(Objects.equals(getConfig().getString("database.type"), "mysql")){
            databaseConnection = DatabaseHandler.connect("mysql://" + getConfig().getString("database.address") + ":" + getConfig().getString("database.port") + "/" + getConfig().getString("database.name"), getConfig().getString("username"), getConfig().getString("database.password"));
        }
        if(Objects.equals(getConfig().getString("database.type"), "mariadb")){
            databaseConnection = DatabaseHandler.connect("mariadb://" + getConfig().getString("database.address") + ":" + getConfig().getString("database.port") + "/" + getConfig().getString("database.name"), getConfig().getString("username"), getConfig().getString("database.password"));
        }

        //Final Init message
        getServer().getConsoleSender().sendMessage("[SimpleCurrencies]" + ChatColor.GREEN + "The plugin has finished initializing.");
    }




    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getLogger().info("[SimpleCurrencies] The plugin is shutting down, please wait...");
        getServer().getLogger().info("[SimpleCurrencies] Saving Player Data...");
        for(Player player : Bukkit.getOnlinePlayers()){
            PlayerConfig.saveConfig(player.getUniqueId().toString());
        }


        getServer().getLogger().info("[SimpleCurrencies] The plugin has finished shutting down.");
    }

    public static SimpleCurrencies getInstance(){
        return instance;
    }

    private boolean checkVersion(){
        try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=86192").openStream();
             Scanner scanner = new Scanner(inputStream)) {
            if (scanner.hasNext()) {
                String checkedVersion = scanner.next();
                scanner.close();
                return checkedVersion.equalsIgnoreCase(version);
            }
            instance.getLogger().info("Cannot look for updates. Is spigot down?");
            return true;
        } catch (IOException exception) {
            instance.getLogger().info("Cannot look for updates: " + exception.getMessage());
            return true;
        }
    }
    public static HikariDataSource getHikariDatabaseConnection(){
        return databaseConnection;
    }
    public Connection getDatabaseConnection(){
        try {
            return databaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
