
package com.github.carthax08.simplecurrencies.commands;

import com.github.carthax08.simplecurrencies.data.PricesConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static com.github.carthax08.simplecurrencies.api.Currencies.*;

public class SellCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length > 0) {
                if (args[0].equalsIgnoreCase("all")) {
                    Inventory inventory = player.getInventory();
                    ItemStack[] items = inventory.getContents();
                    FileConfiguration config = PricesConfig.getConfig();
                    int itemsSold = 0;
                    for (ItemStack item : items) {
                        try {
                            if(item != null) {
                                if (config.isSet("prices." + item.getType().toString().toLowerCase() + ".price")) {
                                    inventory.remove(item);
                                    String stringToCheck = item.getType().toString().toLowerCase();
                                    addCurrency(getSellingCurrency(stringToCheck.toLowerCase()), player, getSellingPrice(stringToCheck) * item.getAmount());
                                    itemsSold += item.getAmount();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            player.sendMessage(ChatColor.RED + "A problem has occurred. Please report this to the server owner and have them report it to the developer!");
                            return true;
                        }
                    }
                    if (itemsSold > 0) {
                        player.sendMessage(ChatColor.GREEN + "Successfully sold " + itemsSold + " items");
                    } else {
                        player.sendMessage(ChatColor.RED + "No items were sold!");
                    }
                    return true;
                }
            }else{
                int itemsSold = 0;
                ItemStack itemToSell = player.getInventory().getItemInMainHand();
                player.sendMessage(itemToSell.getType().toString().toLowerCase());
                if(PricesConfig.getConfig().isSet("prices." + itemToSell.getType().toString().toLowerCase() + ".price")){
                    String stringToCheck = itemToSell.getType().getKey().toString();
                    addCurrency(getSellingCurrency(stringToCheck.toLowerCase()), player, getSellingPrice(stringToCheck) * itemToSell.getAmount());
                    player.getInventory().remove(itemToSell);
                    itemsSold += itemToSell.getAmount();
                    player.sendMessage(ChatColor.GREEN + "Successfully sold " + itemsSold + " " + itemToSell.getType().toString().toLowerCase());
                }
                return true;
            }
            }else{
            sender.sendMessage("You need to be a player to use this command!");
            return true;
        }
        return false;
    }
}
