package com.github.carthax08.simplecurrencies.commands;

import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import com.github.carthax08.simplecurrencies.api.Currencies;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetCommand implements CommandExecutor {

    SimpleCurrencies plugin;

    public GetCommand(SimpleCurrencies main) {
        plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 1) {
            Player player = Bukkit.getPlayer(args[1]);
            sender.sendMessage(args[1] + " has " + Currencies.getCurrency(args[0], player) + "." + args[0] + " " + args[0]);
        } else {
            sender.sendMessage("Insufficient Arguments!");
        }
        return true;
    }
}
