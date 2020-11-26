package com.github.carthax08.simplecurrencies;

import com.github.carthax08.simplecurrencies.commands.MainCommand;
import com.github.carthax08.simplecurrencies.events.onPlayerJoinEvent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class SimpleCurrencies extends JavaPlugin {

    public HashMap<Player, YamlConfiguration> playerConfigMap = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "The plugin is initializing... Please wait.");
        //Command Registration
        getCommand("simplecurrencies").setExecutor(new MainCommand(this));
        //Event Registration
        getServer().getPluginManager().registerEvents(new onPlayerJoinEvent(this), this);
        //Other Registration
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "The pluign has finished initializing. Enjoy!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
