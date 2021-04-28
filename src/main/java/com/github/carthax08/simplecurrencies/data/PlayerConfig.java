package com.github.carthax08.simplecurrencies.data;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PlayerConfig {
    private static File file;
    private static HashMap<String, YamlConfiguration> playerConfigMap = new HashMap<>();

    public static YamlConfiguration createPlayerConfig(String UUID){
        file = new File(Bukkit.getPluginManager().getPlugin("SimpleCurrencies").getDataFolder(), UUID + ".yml");
        if(!file.exists()){
            try {
                file.createNewFile();
                playerConfigMap.put(UUID, YamlConfiguration.loadConfiguration(file));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            playerConfigMap.put(UUID, YamlConfiguration.loadConfiguration(file));
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getConfig(String UUID){
        return playerConfigMap.get(UUID);
    }

    public static boolean saveConfig(String UUID){
        try {
            YamlConfiguration config = playerConfigMap.get(UUID);
            File f = new File(Bukkit.getPluginManager().getPlugin("SimpleCurrencies").getDataFolder(), UUID + ".yml");
            config.save(f);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void removeConfigFromMap(String UUID){
        YamlConfiguration config = playerConfigMap.get(UUID);
        playerConfigMap.remove(UUID);
    }
    public static void reloadConfig(String UUID){
        File file1 = new File(Bukkit.getPluginManager().getPlugin("SimpleCurrencies").getDataFolder(), UUID + ".yml");
        playerConfigMap.replace(UUID, YamlConfiguration.loadConfiguration(file1));
    }

}
