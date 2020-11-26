package com.github.carthax08.simplecurrencies.events;

import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class onPlayerLeaveEvent implements Listener {

    SimpleCurrencies main;

    public onPlayerLeaveEvent(SimpleCurrencies plugin){
        main = plugin;
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        try {
            main.playerConfigMap.get(event.getPlayer()).save(new File(main.getDataFolder() + "/players", event.getPlayer().getUniqueId() + ".yml"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
