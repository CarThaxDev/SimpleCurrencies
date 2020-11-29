package com.github.carthax08.simplecurrencies.api;

import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import com.github.carthax08.simplecurrencies.data.PricesConfig;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;

import static com.github.carthax08.simplecurrencies.api.Config.saveConfigFile;

public class Currencies {
    private static final SimpleCurrencies instance = SimpleCurrencies.getInstance();
    private static final FileConfiguration sellConfig = PricesConfig.getConfig();

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
