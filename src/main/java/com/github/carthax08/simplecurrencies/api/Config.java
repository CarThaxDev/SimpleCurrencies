package com.github.carthax08.simplecurrencies.api;

import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    private static SimpleCurrencies instance = SimpleCurrencies.getInstance();

    public static FileConfiguration getConfigFile(){
        return instance.getConfig();
    }
    public static void saveConfigFile(){
        instance.saveConfig();
    }
    public static Boolean  checkCurrency(String currencyToCheck){
        return instance.getConfig().getBoolean("currencies." + currencyToCheck + ".enabled");
    }
}
