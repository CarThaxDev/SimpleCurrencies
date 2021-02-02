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
    private static HashMap<YamlConfiguration, File> fileConfigMap = new HashMap<>();

    public static void createPlayerConfig(String UUID){
        file = new File(Bukkit.getPluginManager().getPlugin("SimpleCurrencies").getDataFolder(), UUID + ".yml");
        if(!file.exists()){
            try {
                file.createNewFile();
                playerConfigMap.put(UUID, YamlConfiguration.loadConfiguration(file));
                fileConfigMap.put(YamlConfiguration.loadConfiguration(file), file);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            playerConfigMap.put(UUID, YamlConfiguration.loadConfiguration(file));
            fileConfigMap.put(YamlConfiguration.loadConfiguration(file), file);
        }
    }

    public static FileConfiguration getConfig(String UUID){
        return playerConfigMap.get(UUID);
    }

    public static boolean saveConfig(String UUID){
        try {
            YamlConfiguration config = playerConfigMap.get(UUID);
            File f = fileConfigMap.get(config);
            config.save(f);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void reloadConfig(String UUID){
        playerConfigMap.replace(UUID, YamlConfiguration.loadConfiguration(file));
    }

}
