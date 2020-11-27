package com.github.carthax08.simplecurrencies.events;

import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onPlayerJoinEvent implements Listener {

    SimpleCurrencies plugin;

    public onPlayerJoinEvent(SimpleCurrencies main){plugin = main;}

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        if(!plugin.getConfig().getBoolean("players." + event.getPlayer().getUniqueId().toString() + ".hasJoinedBefore")){
            plugin.getConfig().set("players." + event.getPlayer().getUniqueId().toString() + ".hasJoinedBefore", true);
            plugin.saveConfig();
        }
    }
}
