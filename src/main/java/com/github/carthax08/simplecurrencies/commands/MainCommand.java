package com.github.carthax08.simplecurrencies.commands;

import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import com.github.carthax08.simplecurrencies.enums.CommandType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

    SimpleCurrencies plugin;

    public MainCommand(SimpleCurrencies main){
        plugin = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(!player.hasPermission("simplecurrencies.admin")){
                player.sendMessage("You do not have the required permissions to use this command!");
                return true;
            }else{
                if(checkCommandType(args[0]) == CommandType.ADD){
                    if(checkArgsLength(args) == 1){
                        player.sendMessage("Please provide a currency, player, and amount");
                        return false;
                    }else if(checkArgsLength(args) == 2){
                        player.sendMessage("Please provide a player and amount!");
                        return false;
                    }else if(checkArgsLength(args) == 3){
                        player.sendMessage("Please provide an amount!");
                        return false;
                    }
                }
                return true;
            }
        }else{
            return true;
        }
    }
    public CommandType checkCommandType(String stringToCheck){
        if(stringToCheck.equalsIgnoreCase("add")){
            return CommandType.ADD;
        }
        if(stringToCheck.equalsIgnoreCase("set")){
            return CommandType.SET;
        }
        if(stringToCheck.equalsIgnoreCase("remove")){
            return CommandType.REMOVE;
        }
        if(stringToCheck.equalsIgnoreCase("clear")){
            return CommandType.CLEAR;
        }
        return CommandType.UNKNOWN;
    }
    public int checkArgsLength(String[] args){
        return args.length;
    }
}
