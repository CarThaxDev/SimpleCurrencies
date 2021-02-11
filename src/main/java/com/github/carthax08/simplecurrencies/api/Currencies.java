package com.github.carthax08.simplecurrencies.api;

import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import com.github.carthax08.simplecurrencies.data.PlayerConfig;
import com.github.carthax08.simplecurrencies.data.PricesConfig;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;

import static com.github.carthax08.simplecurrencies.api.Config.saveConfigFile;

public class Currencies {
    private static final FileConfiguration sellConfig = PricesConfig.getConfig();

    public static void addCurrency(String currencyToEdit, OfflinePlayer playerToEdit, Double amountToAdd){
        PlayerConfig.getConfig(playerToEdit.getUniqueId().toString()).set(currencyToEdit, PlayerConfig.getConfig(playerToEdit.getUniqueId().toString()).getDouble(currencyToEdit) + amountToAdd);
    }
    public static void setCurrency(String currencyToEdit, OfflinePlayer playerToEdit, Double amountToSet){
        PlayerConfig.getConfig(playerToEdit.getUniqueId().toString()).set(currencyToEdit, amountToSet);
    }
    public static void clearCurrency(String currencyToEdit, OfflinePlayer playerToEdit){
        PlayerConfig.getConfig(playerToEdit.getUniqueId().toString()).set(currencyToEdit, 0);
    }
    public static void removeCurrency(String currencyToEdit, OfflinePlayer playerToEdit, Double amountToRemove){
        PlayerConfig.getConfig(playerToEdit.getUniqueId().toString()).set(currencyToEdit, PlayerConfig.getConfig(playerToEdit.getUniqueId().toString()).getDouble(currencyToEdit) - amountToRemove);
    }
    public static Double getCurrency(String currencyToGet, OfflinePlayer playerToGetFrom){
        return PlayerConfig.getConfig(playerToGetFrom.getUniqueId().toString()).getDouble(currencyToGet);
    }
    public static Double getSellingPrice(String nameToCheck){
        return sellConfig.getDouble("prices." + nameToCheck + ".price");
    }
    public static String getSellingCurrency(String nameToCheck){
        return sellConfig.getString("prices." + nameToCheck + ".currency");
    }
}
