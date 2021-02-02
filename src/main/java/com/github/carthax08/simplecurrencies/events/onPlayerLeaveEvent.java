package com.github.carthax08.simplecurrencies.events;

import com.github.carthax08.simplecurrencies.data.PlayerConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class onPlayerLeaveEvent implements Listener {
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        PlayerConfig.saveConfig(e.getPlayer().getUniqueId().toString());
    }
}
