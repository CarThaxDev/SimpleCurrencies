package com.github.carthax08.simplecurrencies.events;

import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import com.github.carthax08.simplecurrencies.data.PlayerConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class onPlayerJoinEvent implements Listener {

    SimpleCurrencies plugin;

    public onPlayerJoinEvent(SimpleCurrencies main){plugin = main;}

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
            PlayerConfig.loadPlayerConfig(event.getPlayer().getUniqueId().toString());
    }
}
