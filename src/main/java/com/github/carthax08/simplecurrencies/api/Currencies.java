package com.github.carthax08.simplecurrencies.api;

import com.github.carthax08.simplecurrencies.data.PlayerConfig;
import com.github.carthax08.simplecurrencies.data.PricesConfig;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Currencies {
    private static final FileConfiguration sellConfig = PricesConfig.getConfig();

    public static void addCurrency(String currencyToEdit, OfflinePlayer playerToEdit, Double amountToAdd){
        YamlConfiguration config = PlayerConfig.getConfig(playerToEdit.getUniqueId().toString());
        config.set(currencyToEdit, config.getDouble(currencyToEdit) + amountToAdd);
        PlayerConfig.replaceConfigInMap(config, playerToEdit.getUniqueId().toString());
    }
    public static void setCurrency(String currencyToEdit, OfflinePlayer playerToEdit, Double amountToSet){
        YamlConfiguration config = PlayerConfig.getConfig(playerToEdit.getUniqueId().toString());
        config.set(currencyToEdit, amountToSet);
        PlayerConfig.replaceConfigInMap(config, playerToEdit.getUniqueId().toString());
    }
    public static void clearCurrency(String currencyToEdit, OfflinePlayer playerToEdit){
        YamlConfiguration config = PlayerConfig.getConfig(playerToEdit.getUniqueId().toString());
        config.set(currencyToEdit, 0);
        PlayerConfig.replaceConfigInMap(config, playerToEdit.getUniqueId().toString());
    }
    public static void removeCurrency(String currencyToEdit, OfflinePlayer playerToEdit, Double amountToRemove){
        YamlConfiguration config = PlayerConfig.getConfig(playerToEdit.getUniqueId().toString());
        config.set(currencyToEdit, config.getDouble(currencyToEdit) - amountToRemove);
        PlayerConfig.replaceConfigInMap(config, playerToEdit.getUniqueId().toString());
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
