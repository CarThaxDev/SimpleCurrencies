package com.github.carthax08.simplecurrencies.data;

import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import com.github.carthax08.simplecurrencies.api.Config;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PlayerConfig {
    private static File file;
    private static HashMap<String, PluginPlayer> playerConfigMap = new HashMap<>();

    public static YamlConfiguration loadPlayerConfig(String UUID){
        System.out.println(SimpleCurrencies.getInstance().getDataFolder());
        file = new File(SimpleCurrencies.getInstance().getDataFolder() + File.separator + "player-data", UUID + ".yml");
        if(!file.exists()){
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                List<String> currencyList = Config.getCurrencyList();
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                for(String currency : currencyList){
                    config.set(currency, 0);
                }
                playerConfigMap.put(UUID, new PluginPlayer(YamlConfiguration.loadConfiguration(file)));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            playerConfigMap.put(UUID, new PluginPlayer(YamlConfiguration.loadConfiguration(file)));
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public static PluginPlayer getConfig(String UUID){
        return playerConfigMap.get(UUID);
    }

    public static void saveConfig(String UUID){
        try {
            PluginPlayer config = playerConfigMap.get(UUID);
            File f = new File(SimpleCurrencies.getInstance().getDataFolder() + File.separator + "player-data", UUID + ".yml");
            config.config.save(f);
            removeConfigFromMap(UUID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void removeConfigFromMap(String UUID){
        playerConfigMap.remove(UUID);
    }
    public static void reloadConfig(String UUID) {
        File file1 = new File(SimpleCurrencies.getInstance().getDataFolder(), UUID + ".yml");
        playerConfigMap.replace(UUID, new PluginPlayer(YamlConfiguration.loadConfiguration(file)));
    }


}
