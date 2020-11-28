package com.github.carthax08.simplecurrencies;

import com.github.carthax08.simplecurrencies.PapiExpansion.SimpleCurrenciesExpansion;
import com.github.carthax08.simplecurrencies.commands.GetCommand;
import com.github.carthax08.simplecurrencies.commands.MainCommand;
import com.github.carthax08.simplecurrencies.events.onPlayerJoinEvent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.print.attribute.standard.Severity;
import java.util.HashMap;

public final class SimpleCurrencies extends JavaPlugin {

    public HashMap<Player, FileConfiguration> playerConfigMap = new HashMap<>();
    private static SimpleCurrencies instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "The plugin is initializing... Please wait.");
        //Command Registration
        getCommand("simplecurrencies").setExecutor(new MainCommand(this));
        getCommand("getcurrency").setExecutor(new GetCommand(this));
        //Event Registration
        getServer().getPluginManager().registerEvents(new onPlayerJoinEvent(this), this);
        //Other Registration
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        if(!getConfig().getBoolean("settings.hasBeenEdited")){
            getServer().getLogger().warning("[SimpleCurrencies] You are still using the default config! Please edit it.");
        }

        instance = this;

        new SimpleCurrenciesExpansion(this);

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "The pluign has finished initializing. Enjoy!");
    }




    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

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
    public static void getCurrency(String currencyToGet, Player playerToGetFrom){
        instance.getConfig().get("players." + playerToGetFrom.getUniqueId().toString() + "." + currencyToGet);
    }
}
