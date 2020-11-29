package com.github.carthax08.simplecurrencies;

import com.github.carthax08.simplecurrencies.PapiExpansion.SimpleCurrenciesExpansion;
import com.github.carthax08.simplecurrencies.commands.GetCommand;
import com.github.carthax08.simplecurrencies.commands.MainCommand;
import com.github.carthax08.simplecurrencies.commands.PayCommand;
import com.github.carthax08.simplecurrencies.commands.SellCommand;
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
        saveConfigFile();
        getServer().getLogger().info("[SimpleCurrencies] Config Saved...");
        getServer().getLogger().info("[SimpleCurrencies] The plugin has finished shutting down.");
    }

    //Basic Config-Based API API
    public static FileConfiguration getConfigFile(){
        return instance.getConfig();
    }
    public static void saveConfigFile(){
        instance.saveConfig();
    }
    public static Boolean  checkCurrency(String currencyToCheck){
        return instance.getConfig().getBoolean("currencies." + currencyToCheck + ".enabled");
    }



    //API for Online Players
    public static void removeCurrency(String currencyToEdit, Player playerToEdit, Double amountToRemove){
        instance.getConfig().set("players." + playerToEdit.getUniqueId().toString() + "." + currencyToEdit, instance.getConfig().getInt("players." + playerToEdit.getUniqueId().toString() + "." + currencyToEdit) - amountToRemove);
        saveConfigFile();
    }
    public static void addCurrency(String currencyToEdit, Player playerToEdit, Double amountToAdd){
        instance.getConfig().set("players." + playerToEdit.getUniqueId().toString() + "." + currencyToEdit, instance.getConfig().getInt("players." + playerToEdit.getUniqueId().toString() + "." + currencyToEdit) + amountToAdd);
        saveConfigFile();
    }
    public static void setCurrency(String currencyToEdit, Player playerToEdit, Double amountToSet){
        instance.getConfig().set("players." + playerToEdit.getUniqueId().toString() + "." + currencyToEdit, amountToSet);
        saveConfigFile();
    }
    public static void clearCurrency(String currencyToEdit, Player playerToEdit){
        instance.getConfig().set("players." + playerToEdit.getUniqueId().toString() + "." + currencyToEdit, 0);
        saveConfigFile();
    }
    public static Double getCurrency(String currencyToGet, Player playerToGetFrom){
        return instance.getConfig().getDouble("players." + playerToGetFrom.getUniqueId().toString() + "." + currencyToGet);
    }

    public static SimpleCurrencies getInstance(){
        return instance;
    }

    //API for Offline Players
    public static void addCurrency(String currencyToEdit, OfflinePlayer playerToEdit, Double amountToAdd){
        instance.getConfig().set("players." + playerToEdit.getUniqueId().toString() + "." + currencyToEdit, instance.getConfig().getDouble("players." + playerToEdit.getUniqueId().toString() + "." + currencyToEdit) + amountToAdd);
        saveConfigFile();
    }
    public static void setCurrency(String currencyToEdit, OfflinePlayer playerToEdit, Double amountToSet){
        instance.getConfig().set("players." + playerToEdit.getUniqueId().toString() + "." + currencyToEdit, amountToSet);
        saveConfigFile();
    }
    public static void clearCurrency(String currencyToEdit, OfflinePlayer playerToEdit){
        instance.getConfig().set("players." + playerToEdit.getUniqueId().toString() + "." + currencyToEdit, 0);
        saveConfigFile();
    }
    public static void removeCurrency(String currencyToEdit, OfflinePlayer playerToEdit, Double amountToRemove){
        instance.getConfig().set("players." + playerToEdit.getUniqueId().toString() + "." + currencyToEdit, instance.getConfig().getDouble("players." + playerToEdit.getUniqueId().toString() + "." + currencyToEdit) - amountToRemove);
        saveConfigFile();
        saveConfigFile();
    }
    public static Double getCurrency(String currencyToGet, OfflinePlayer playerToGetFrom){
        return instance.getConfig().getDouble("players." + playerToGetFrom.getUniqueId().toString() + "." + currencyToGet);
    }
    public static Double getSellingPrice(String nameToCheck){
        return sellConfig.getDouble("prices." + nameToCheck + ".value");
    }
    public static String getSellingCurrency(String nameToCheck){
        return sellConfig.getString("prices." + nameToCheck + ".currency");
    }
}
