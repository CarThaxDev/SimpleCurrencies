package com.github.carthax08.simplecurrencies.commands;

import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetCommand implements CommandExecutor {

    SimpleCurrencies plugin;
    public GetCommand(SimpleCurrencies main){
        plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
         Player player = (Player) sender;
         if(args.length <= 1){
             player.sendMessage("Insufficient Arguments!");
             return false;
         }else{
             Player player2 = Bukkit.getPlayer(args[1]);
             sender.sendMessage(String.valueOf(plugin.getConfig().getInt("players." + player2.getUniqueId().toString() + "." + args[0])));
             return true;
         }
    }else{
            if(args.length <= 1){
                sender.sendMessage("Insufficient Arguments!");
                return false;
            }else{
                Player player = Bukkit.getPlayer(args[1]);
                sender.sendMessage(args[1] + " has " + plugin.getConfig().getInt("players." + player.getUniqueId().toString() + "." + args[0]) + " " + args[0]);
                return true;
            }
        }
    }
}
