package com.github.carthax08.simplecurrencies.events;

import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;

import static javax.sql.rowset.spi.SyncFactory.getLogger;

public class onPlayerJoinEvent implements Listener {
    YamlConfiguration config;
    SimpleCurrencies plugin;
    public onPlayerJoinEvent(SimpleCurrencies main){
        plugin = main;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){


        File f=new File(plugin.getDataFolder(), event.getPlayer().getUniqueId()+".yml");
        if(f.exists()){
            config= YamlConfiguration.loadConfiguration(f);
            Bukkit.getServer().getConsoleSender().sendMessage("Loading player data for " + event.getPlayer().getName());
            plugin.playerConfigMap.put(event.getPlayer(), config);
        }
        else
            try {

                plugin.getDataFolder().createNewFile();
                config=YamlConfiguration.loadConfiguration(f);
                plugin.playerConfigMap.put(event.getPlayer(), config);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
