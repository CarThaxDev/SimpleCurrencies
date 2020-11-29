
package com.github.carthax08.simplecurrencies.commands;

import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import com.github.carthax08.simplecurrencies.data.PricesConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SellCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){

            Player player = (Player) sender;
            Inventory inventory = player.getInventory();
            ItemStack[] items = inventory.getContents();
            FileConfiguration config = PricesConfig.getConfig();
            List<?> list = config.getList("items");
            int itemsSold = 0;
            for(int i = 1; i < items.length; i++){
                assert list != null;
                if(list.contains(items[i].getType().toString())){
                    inventory.remove(items[i]);
                    String stringToCheck = items[i].getType().toString();
                    SimpleCurrencies.addCurrency(SimpleCurrencies.getSellingCurrency(stringToCheck.toLowerCase()), player, SimpleCurrencies.getSellingPrice(stringToCheck) * items[i].getAmount());
                    itemsSold += items[i].getAmount();
                }
            }
            if(itemsSold > 0){
                player.sendMessage("Successfully sold " + itemsSold + " items");
            }
        }else{
            sender.sendMessage("You need to be a player to use this command!");
        }
        return false;
    }
}
