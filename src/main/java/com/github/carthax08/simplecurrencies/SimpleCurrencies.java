package com.github.carthax08.simplecurrencies;

import com.github.carthax08.simplecurrencies.PapiExpansion.SimpleCurrenciesExpansion;
import com.github.carthax08.simplecurrencies.commands.*;
import com.github.carthax08.simplecurrencies.data.PricesConfig;
import com.github.carthax08.simplecurrencies.events.onPlayerJoinEvent;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleCurrencies extends JavaPlugin {

    private static SimpleCurrencies instance;
    private static FileConfiguration sellConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic
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

        //API Setup
        instance = this;

        //PlaceholderAPI Registration
        if(getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new SimpleCurrenciesExpansion(this);
        }else{
            getServer().getLogger().info("[SimpleCurrencies] PlaceholderAPI is not installed! it is recommended you install it!");
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
