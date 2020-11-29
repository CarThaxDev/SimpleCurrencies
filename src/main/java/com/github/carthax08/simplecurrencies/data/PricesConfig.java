package com.github.carthax08.simplecurrencies.data;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PricesConfig {
    private static File file;
    private static FileConfiguration customConfig;
    private static List<String> list = new ArrayList<String>();

    public static void setupConfig(){
        file = new File(Bukkit.getPluginManager().getPlugin("SimpleCurrencies").getDataFolder(), "prices.yml");
        if(!file.exists()){
            try {
                file.createNewFile();
                customConfig = YamlConfiguration.loadConfiguration(file);
                addDefaultValues();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            for(int i = 0; i < customConfig.getList("items").size(); i++){
                list.add(customConfig.getList("items").get(i).toString());
            }
        }
    }

    private static void addDefaultValues() {

        list.add("coal");
        list.add("iron_ore");
        list.add("gold_ore");
        list.add("diamond");
        list.add("emerald");
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
        customConfig.set("items", list);
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
