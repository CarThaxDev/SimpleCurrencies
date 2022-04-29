package com.github.carthax08.simplecurrencies.events;

import com.github.carthax08.simplecurrencies.data.PlayerConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onPlayerJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
            PlayerConfig.loadPlayerConfig(event.getPlayer().getUniqueId().toString());
    }
}
