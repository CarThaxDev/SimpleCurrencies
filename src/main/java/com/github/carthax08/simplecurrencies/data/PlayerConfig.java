package com.github.carthax08.simplecurrencies.data;

import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import com.github.carthax08.simplecurrencies.api.Config;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class PlayerConfig {
    private static File file;
    private static HashMap<String, YamlConfiguration> playerConfigMap = new HashMap<>();

    public static YamlConfiguration loadPlayerConfig(String UUID){
        file = new File(SimpleCurrencies.getInstance().getDataFolder(), UUID + ".yml");
        if(!file.exists()){
            try {
                file.createNewFile();
                List<String> currencyList = Config.getCurrencyList();
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                for(String currency : currencyList){
                    config.set(currency, 0);
                }
                playerConfigMap.put(UUID, config);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            playerConfigMap.put(UUID, YamlConfiguration.loadConfiguration(file));
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public static YamlConfiguration getConfig(String UUID){
        return playerConfigMap.get(UUID);
    }

    public static void saveConfig(String UUID){
        try {
            YamlConfiguration config = playerConfigMap.get(UUID);
            System.out.println(SimpleCurrencies.getInstance().getDataFolder().toString());
            File f = new File(SimpleCurrencies.getInstance().getDataFolder(), UUID + ".yml");
            config.save(f);
            removeConfigFromMap(UUID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void removeConfigFromMap(String UUID){
        YamlConfiguration config = playerConfigMap.get(UUID);
        playerConfigMap.remove(UUID);
    }
    public static void reloadConfig(String UUID){
        File file1 = new File(SimpleCurrencies.getInstance().getDataFolder(), UUID + ".yml");
        playerConfigMap.replace(UUID, YamlConfiguration.loadConfiguration(file1));
    }

    public static void replaceConfigInMap(YamlConfiguration config, String UUID){
        if(playerConfigMap.containsKey(UUID)){
            playerConfigMap.replace(UUID, config);
        }
    }


}
