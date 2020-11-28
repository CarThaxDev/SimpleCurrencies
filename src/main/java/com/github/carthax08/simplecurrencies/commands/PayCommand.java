package com.github.carthax08.simplecurrencies.commands;

import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

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
                if(SimpleCurrencies.getCurrency(args[0], player) < Integer.parseInt(args[2])){
                    player.sendMessage("You do not have enough " + args[0] + " to fulfill the payment!");
                }else{
                    @NotNull Player player2 = Objects.requireNonNull(Bukkit.getPlayer(args[1]));
                    SimpleCurrencies.removeCurrency(args[0], player, Integer.parseInt(args[2]));
                    SimpleCurrencies.addCurrency(args[0], player2, Integer.parseInt(args[2]));
                }
            }
        }else{
            sender.sendMessage("You must be a player to use this command!");
            return false;
        }



        return false;
    }
}
