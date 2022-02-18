package com.github.carthax08.simplecurrencies;

import com.github.carthax08.simplecurrencies.PapiExpansion.SimpleCurrenciesExpansion;
import com.github.carthax08.simplecurrencies.commands.*;
import com.github.carthax08.simplecurrencies.data.PlayerConfig;
import com.github.carthax08.simplecurrencies.data.PricesConfig;
import com.github.carthax08.simplecurrencies.events.onPlayerJoinEvent;
import com.github.carthax08.simplecurrencies.events.onPlayerLeaveEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

public final class SimpleCurrencies extends JavaPlugin {

    private static SimpleCurrencies instance;
    private Random rand;
    private static String version = "3.1.0";

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
        getServer().getPluginManager().registerEvents(new onPlayerJoinEvent(this), this);
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

        //Rating Message
        int i = rand.nextInt(1000);
        if(i > 10 && i < 15){
            getServer().getConsoleSender().sendMessage("[SimpleCurrencies] " + ChatColor.YELLOW + "If you haven't already, please leave a rating on Spigot.");
        }
        //Final Init message
        getServer().getConsoleSender().sendMessage("[SimpleCurrencies]" + ChatColor.GREEN + "The plugin has finished initializing. Enjoy!");
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


}
