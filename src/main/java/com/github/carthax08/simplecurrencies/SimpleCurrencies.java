package com.github.carthax08.simplecurrencies;

import com.github.carthax08.simplecurrencies.PapiExpansion.SimpleCurrenciesExpansion;
import com.github.carthax08.simplecurrencies.commands.GetCommand;
import com.github.carthax08.simplecurrencies.commands.MainCommand;
import com.github.carthax08.simplecurrencies.commands.PayCommand;
import com.github.carthax08.simplecurrencies.events.onPlayerJoinEvent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.print.attribute.standard.Severity;
import java.util.HashMap;

public final class SimpleCurrencies extends JavaPlugin {

    private static SimpleCurrencies instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage("[SimpleCurrencies]" + ChatColor.GREEN + "The plugin is initializing... Please wait.");

        //Command Registration
        getCommand("simplecurrencies").setExecutor(new MainCommand(this));
        getCommand("getcurrency").setExecutor(new GetCommand(this));
        getCommand("sendcurrency").setExecutor(new PayCommand());

        //Event Registration
        getServer().getPluginManager().registerEvents(new onPlayerJoinEvent(this), this);

        //Config Registration
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        if(!getConfig().getBoolean("settings.hasBeenEdited")){
            getServer().getLogger().warning("[SimpleCurrencies] You are still using the default config! Please edit it.");
        }

        //API Registration
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
        saveConfigFile();
        getServer().getLogger().info("[SimpleCurrencies] Config Saved...");
        getServer().getLogger().info("[SimpleCurrencies] The plugin has finished shutting down.");
    }

    //Public API
    public static FileConfiguration getConfigFile(){
        return instance.getConfig();
    }
    public static void saveConfigFile(){
        instance.saveConfig();
    }

    public static void removeCurrency(String currencyToEdit, Player playerToEdit, int amountToRemove){
        instance.getConfig().set("players." + playerToEdit.getUniqueId().toString() + "." + currencyToEdit, instance.getConfig().getInt("players." + playerToEdit.getUniqueId().toString() + "." + currencyToEdit) - amountToRemove);
        saveConfigFile();
        saveConfigFile();
    }
    public static void addCurrency(String currencyToEdit, Player playerToEdit, int amountToAdd){
        instance.getConfig().set("players." + playerToEdit.getUniqueId().toString() + "." + currencyToEdit, instance.getConfig().getInt("players." + playerToEdit.getUniqueId().toString() + "." + currencyToEdit) + amountToAdd);
        saveConfigFile();
    }
    public static void setCurrency(String currencyToEdit, Player playerToEdit, int amountToSet){
        instance.getConfig().set("players." + playerToEdit.getUniqueId().toString() + "." + currencyToEdit, amountToSet);
        saveConfigFile();
    }
    public static void clearCurrency(String currencyToEdit, Player playerToEdit){
        instance.getConfig().set("players." + playerToEdit.getUniqueId().toString() + "." + currencyToEdit, 0);
        saveConfigFile();
    }
    public static int getCurrency(String currencyToGet, Player playerToGetFrom){
        return instance.getConfig().getInt("players." + playerToGetFrom.getUniqueId().toString() + "." + currencyToGet);
    }
}
