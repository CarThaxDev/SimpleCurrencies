package com.github.carthax08.simplecurrencies.listeners;

import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import com.github.carthax08.simplecurrencies.data.PlayerConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private SimpleCurrencies plugin;

    public PlayerJoinListener(SimpleCurrencies main) {
        this.plugin = main;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
            PlayerConfig.loadPlayerConfig(event.getPlayer().getUniqueId().toString());
    }
}
