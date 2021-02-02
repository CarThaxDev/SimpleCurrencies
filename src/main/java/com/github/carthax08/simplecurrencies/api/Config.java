package com.github.carthax08.simplecurrencies.api;

import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Config {
    private static SimpleCurrencies instance = SimpleCurrencies.getInstance();

    public static FileConfiguration getConfigFile(){
        return instance.getConfig();
    }
    public static void saveConfigFile(){
        instance.saveConfig();
    }
    public static Boolean checkCurrency(String currencyToCheck){
        return instance.getConfig().getStringList("currencies").contains(currencyToCheck);
    }
    public static List<String> getCurrencyList(){
        return instance.getConfig().getStringList("currencies");
    }
}
