package com.github.carthax08.simplecurrencies.events;

import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import com.github.carthax08.simplecurrencies.api.Config;
import com.github.carthax08.simplecurrencies.data.PlayerConfig;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class onPlayerJoinEvent implements Listener {

    SimpleCurrencies plugin;

    public onPlayerJoinEvent(SimpleCurrencies main){plugin = main;}

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        if(!event.getPlayer().hasPlayedBefore()){
            String UUID = event.getPlayer().getUniqueId().toString();
            YamlConfiguration config = PlayerConfig.createPlayerConfig(UUID);
            List<String> currencyList = Config.getCurrencyList();
            for(String currency : currencyList){
                config.set(currency, 0);
            }
            PlayerConfig.replaceConfigInMap(config, UUID);
            PlayerConfig.saveConfig(event.getPlayer().getUniqueId().toString());
        }else{
            PlayerConfig.createPlayerConfig(event.getPlayer().getUniqueId().toString());
        }
    }
}
