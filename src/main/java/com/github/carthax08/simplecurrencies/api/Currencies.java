package com.github.carthax08.simplecurrencies.api;

import com.github.carthax08.simplecurrencies.data.PlayerConfig;
import com.github.carthax08.simplecurrencies.data.PluginPlayer;
import com.github.carthax08.simplecurrencies.data.PricesConfig;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Currencies {
    private static final FileConfiguration sellConfig = PricesConfig.getConfig();

    public static void addCurrency(String currencyToEdit, OfflinePlayer playerToEdit, Double amountToAdd){
        PluginPlayer player = PlayerConfig.getConfig(playerToEdit.getUniqueId().toString());
        player.addCurrency(currencyToEdit, amountToAdd);

    }
    public static void setCurrency(String currencyToEdit, OfflinePlayer playerToEdit, Double amountToSet){
        PluginPlayer player = PlayerConfig.getConfig(playerToEdit.getUniqueId().toString());
        player.setCurrency(currencyToEdit, amountToSet);
    }
    public static void clearCurrency(String currencyToEdit, OfflinePlayer playerToEdit){
        PluginPlayer player = PlayerConfig.getConfig(playerToEdit.getUniqueId().toString());
        player.clearCurrency(currencyToEdit);

    }
    public static boolean removeCurrency(String currencyToEdit, OfflinePlayer playerToEdit, Double amountToRemove){
        PluginPlayer player = PlayerConfig.getConfig(playerToEdit.getUniqueId().toString());
        return player.removeCurrency(currencyToEdit, amountToRemove);
    }
    public static Double getCurrency(String currencyToGet, OfflinePlayer playerToGetFrom){
        return PlayerConfig.getConfig(playerToGetFrom.getUniqueId().toString()).getCurrency(currencyToGet);
    }
    public static Double getSellingPrice(String nameToCheck){
        return sellConfig.getDouble("prices." + nameToCheck + ".price");
    }
    public static String getSellingCurrency(String nameToCheck){
        return sellConfig.getString("prices." + nameToCheck + ".currency");
    }
}
