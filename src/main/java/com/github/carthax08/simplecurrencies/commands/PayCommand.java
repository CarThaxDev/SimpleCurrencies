package com.github.carthax08.simplecurrencies.commands;

import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.github.carthax08.simplecurrencies.api.Config.*;
import static com.github.carthax08.simplecurrencies.api.Currencies.*;

public class PayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length == 0){
                player.sendMessage("You need to provide a currency to send, player to send to, and amount!");
                return true;
            }else if(args.length < 3){
                player.sendMessage("Insufficient arguments!");
                return false;
            }else if(args.length > 3){
                player.sendMessage("Too many arguments!");
                return false;
            }else{
                if(getCurrency(args[0], player) < Double.parseDouble(args[2])){
                    player.sendMessage("You do not have enough " + args[0] + " to fulfill the payment!");
                }else{
                    for(Player player1: Bukkit.getOnlinePlayers()){
                        if(player1.getName().equals(args[1])){
                            removeCurrency(args[0], player, Double.parseDouble(args[2]));
                            addCurrency(args[0], player1, Double.parseDouble(args[2]));
                            player.sendMessage(ChatColor.GREEN + "You successfully sent " + args[2] + " " + args[0] + " to " + player1.getName());
                            player1.sendMessage(ChatColor.GREEN + player.getName() + " has sent you " + args[2] + " " + args[0] + ".");
                            return true;
                        }
                    }
                    for(OfflinePlayer player1 : Bukkit.getOfflinePlayers()){
                        if(player1.getName().equals(args[1])) {
                            if (player1.hasPlayedBefore()) {
                                removeCurrency(args[0], player, Double.parseDouble(args[2]));
                                addCurrency(args[0], player1, Double.parseDouble(args[2]));
                                player.sendMessage(ChatColor.GREEN + "You successfully sent " + args[2] + " " + args[0] + " to " + player1.getName() + ".");
                                return true;
                            }else{
                                player.sendMessage("That player does not exist!");
                            }
                        }
                    }
                }
            }
        }else{
            sender.sendMessage("You must be a player to use this command!");
            return false;
        }



        return false;
    }
}
