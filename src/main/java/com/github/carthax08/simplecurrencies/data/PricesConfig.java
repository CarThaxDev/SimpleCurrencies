package com.github.carthax08.simplecurrencies.data;

import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import org.apache.commons.lang.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PricesConfig {
    private static File file;
    private static FileConfiguration customConfig;

    public static void setupConfig(){
        file = new File(SimpleCurrencies.getInstance().getDataFolder(), "prices.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
                customConfig = YamlConfiguration.loadConfiguration(file);
                addDefaultValues();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
                if (e instanceof NullPointerException) {
                    System.out.println(file);
                }
            }
        }
        customConfig = YamlConfiguration.loadConfiguration(file);
    }

    private static void addDefaultValues() {

        //Prices
        customConfig.set("prices.coal.price", 0.50);
        customConfig.set("prices.iron_ore.price", 3.00);
        customConfig.set("prices.gold_ore.price", 5.00);
        customConfig.set("prices.diamond.price", 10.00);
        customConfig.set("prices.emerald.price", 20.00);
        //Currencies
        customConfig.set("prices.coal.currency", "money");
        customConfig.set("prices.iron_ore.currency", "money");
        customConfig.set("prices.gold_ore.currency", "money");
        customConfig.set("prices.diamond.currency", "money");
        customConfig.set("prices.emerald.currency", "money");
        saveConfig();
    }

    public static FileConfiguration getConfig(){
        return customConfig;
    }

    public static void saveConfig(){
        try {
            customConfig.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void reloadConfig(){
        customConfig = YamlConfiguration.loadConfiguration(file);
    }
}
