package com.github.carthax08.simplecurrencies;

import com.github.carthax08.simplecurrencies.PapiExpansion.SimpleCurrenciesExpansion;
import com.github.carthax08.simplecurrencies.commands.*;
import com.github.carthax08.simplecurrencies.events.onPlayerJoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class SimpleCurrencies extends JavaPlugin {

    private static SimpleCurrencies instance;
    private static FileConfiguration sellConfig;
    private Random rand;

    @Override
    public void onEnable() {
        // Plugin startup logic
        rand = new Random();
        getServer().getConsoleSender().sendMessage("[SimpleCurrencies]" + ChatColor.GREEN + "The plugin is initializing... Please wait.");
        //Command Registration
        getCommand("simplecurrencies").setExecutor(new MainCommand(this));
        getCommand("getcurrency").setExecutor(new GetCommand(this));
        getCommand("sendcurrency").setExecutor(new PayCommand());
        getCommand("balance").setExecutor(new BalanceCommand());
        //getCommand("sell").setExecutor(new SellCommand());

        //Event Registration
        getServer().getPluginManager().registerEvents(new onPlayerJoinEvent(this), this);

        //Config Registration
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Shop Config Registration
        //PricesConfig.setupConfig();
        //sellConfig = PricesConfig.getConfig();

        if(!getConfig().getBoolean("settings.hasBeenEdited")){
            getServer().getLogger().warning("[SimpleCurrencies] You are still using the default config! Please edit it.");
        }
        //Placeholders setup
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            new SimpleCurrenciesExpansion(this).register();
        }
        //API Setup
        instance = this;

        //Rating Message
        int i = rand.nextInt(100);
        if(i > 10 && i < 15){
            getServer().getConsoleSender().sendMessage("[SimpleCurrencies] " + ChatColor.YELLOW + "If you haven't already, please leave a rating on Spigot.");
        }
        //Final Init message
        getServer().getConsoleSender().sendMessage("[SimpleCurrencies]" + ChatColor.GREEN + "The pluign has finished initializing. Enjoy!");
    }




    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getLogger().info("[SimpleCurrencies] The plugin is shutting down, please wait...");
        getServer().getLogger().info("[SimpleCurrencies] Saving Config...");
        saveConfig();
        getServer().getLogger().info("[SimpleCurrencies] Config Saved...");
        getServer().getLogger().info("[SimpleCurrencies] The plugin has finished shutting down.");
    }

    public static SimpleCurrencies getInstance(){
        return instance;
    }

}
