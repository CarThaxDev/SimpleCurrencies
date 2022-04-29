package com.github.carthax08.simplecurrencies.data;

import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class PricesConfig {
    private static File file;
    private static FileConfiguration customConfig;

    public static void setupConfig(){
        File file = new File(SimpleCurrencies.getInstance().getDataFolder() + "prices.yml");
        SimpleCurrencies.getInstance().saveResource("prices.yml", false);
        customConfig = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getConfig(){
        return customConfig;
    }

    public static void reloadConfig(){
        customConfig = YamlConfiguration.loadConfiguration(file);
    }
}
