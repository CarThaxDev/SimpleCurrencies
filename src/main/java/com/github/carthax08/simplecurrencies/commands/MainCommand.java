package com.github.carthax08.simplecurrencies.commands;

import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import com.github.carthax08.simplecurrencies.data.PlayerConfig;
import com.github.carthax08.simplecurrencies.data.PricesConfig;
import com.github.carthax08.simplecurrencies.enums.CommandType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.github.carthax08.simplecurrencies.api.Config.checkCurrency;
import static com.github.carthax08.simplecurrencies.api.Currencies.*;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Check if command sender is console
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to run this command!");
            return false;
        }

        Player plr = (Player) sender;

        // Check if player has permission
        if(!plr.hasPermission("simplecurrencies.admin")) {
            plr.sendMessage("You do not have the required permissions to use this command!");
            return false;
        }

        // Check for no arguments
        if(args.length == 0) {
            plr.sendMessage(ChatColor.RED + "USAGE: " + command.getUsage());
            return true;
        }

        if(checkCommandType(args[0]) == CommandType.RELOAD) {
            SimpleCurrencies.getInstance().reloadConfig();
            PricesConfig.reloadConfig();
            for (Player player1 : Bukkit.getOnlinePlayers()) {
                PlayerConfig.reloadConfig(player1.getUniqueId().toString());
            }
            plr.sendMessage(ChatColor.GREEN + "Successfully reload the config");
        }

        /*for (Player player1 : Bukkit.getOnlinePlayers()) {
            if (player1.getName().equals(args[2])) {
                return handleCommand(player1, args, plr, command);
            }
        }
        for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
            if (offlinePlayer.getName().equals(args[2]) && offlinePlayer.hasPlayedBefore()) {
                return handleCommand(offlinePlayer, args, plr, command);
            }
        }*/

        //plr.sendMessage(ChatColor.RED + "USAGE: " + command.getUsage());
        return true;
    }


    public CommandType checkCommandType(String stringToCheck){
        try {
            return CommandType.valueOf(stringToCheck.toUpperCase());
        } catch(IllegalArgumentException ex) {
            return CommandType.UNKNOWN;
        }
    }
    public int checkArgsLength(String[] args){
        return args.length;
    }

    public boolean handleCommand(OfflinePlayer playerToEdit, String[] args, Player player, Command command){
        if(checkCommandType(args[0]) == CommandType.ADD){
            if(checkArgsLength(args) == 1){
                player.sendMessage(ChatColor.RED + "USAGE: " + command.getUsage());
                return false;
            }else if(checkArgsLength(args) == 2){
                player.sendMessage(ChatColor.RED + "USAGE: " + command.getUsage());
                return false;
            }else if(checkArgsLength(args) == 3){
                player.sendMessage(ChatColor.RED + "USAGE: " + command.getUsage());
                return false;
            }else{
                if(checkCurrency(args[1])){
                    addCurrency(args[1], playerToEdit, Double.parseDouble(args[3]));
                    player.sendMessage(ChatColor.GREEN + "Successfully added " + args[3] + " " + args[1].toLowerCase() + " to player " + args[2]);
                    return true;
                }else{
                    player.sendMessage("Unable to perform request. Either the currency doesn't exist or the config isn't being read properly. Please check the config or alert the owner!");
                    return false;
                }
            }
        }
        if(checkCommandType(args[0]) == CommandType.SET){
            if(checkArgsLength(args) == 1){
                player.sendMessage(ChatColor.RED + "USAGE: " + command.getUsage());
                return false;
            }else if(checkArgsLength(args) == 2){
                player.sendMessage(ChatColor.RED + "USAGE: " + command.getUsage());
                return false;
            }else if(checkArgsLength(args) == 3){
                player.sendMessage(ChatColor.RED + "USAGE: " + command.getUsage());
                return false;
            }else{
                if(checkCurrency(args[1])){
                    setCurrency(args[1], playerToEdit, Double.parseDouble(args[3]));
                    player.sendMessage(ChatColor.GREEN + "Successfully set "+ args[1].toLowerCase() + " of player " + args[2] + " to " + args[3]);
                    return true;
                }else{
                    player.sendMessage("Unable to perform request. Either the currency doesn't exist or it isn't enabled. Please check the config!");
                    return false;
                }
            }
        }
        if(checkCommandType(args[0]) == CommandType.REMOVE){
            if(checkArgsLength(args) == 1){
                player.sendMessage("Please provide a currency, player, and amount");
                return false;
            }else if(checkArgsLength(args) == 2){
                player.sendMessage("Please provide a player and amount!");
                return false;
            }else if(checkArgsLength(args) == 3){
                player.sendMessage("Please provide an amount!");
                return false;
            }else{
                if(checkCurrency(args[1])){
                    removeCurrency(args[1], playerToEdit, Double.parseDouble(args[3]));
                    player.sendMessage(ChatColor.GREEN + "Successfully removed " + args[3] + " " + args[1].toLowerCase() + " from player " + args[2]);
                    return true;
                }else{
                    player.sendMessage("Unable to perform request. Either the currency doesn't exist or the config isn't being read properly. Please check the config or alert the owner!");
                    return false;
                }
            }
        }
        if(checkCommandType(args[0]) == CommandType.CLEAR){
            if(checkArgsLength(args) == 1){
                player.sendMessage("Please provide a currency, player, and amount");
                return false;
            }else if(checkArgsLength(args) == 2){
                player.sendMessage("Please provide a player and amount!");
                return false;
            }else{
                if(checkCurrency(args[1])){
                    clearCurrency(args[1], playerToEdit);
                    player.sendMessage(ChatColor.GREEN + "Successfully cleared " + args[2] + "'s " + args[1]);
                    SimpleCurrencies.getInstance().saveConfig();
                    return true;
                }else{
                    player.sendMessage("Unable to perform request. Either the currency doesn't exist or the config isn't being read properly. Please check the config or alert the owner!");
                    return false;
                }
            }
        }
        return false;
    }
}