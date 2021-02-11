package com.github.carthax08.simplecurrencies.PapiExpansion;

import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import com.github.carthax08.simplecurrencies.api.Config;
import com.github.carthax08.simplecurrencies.api.Currencies;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class SimpleCurrenciesExpansion extends PlaceholderExpansion {
    private SimpleCurrencies plugin;

    public SimpleCurrenciesExpansion(SimpleCurrencies plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean persist(){
        return true;
    }

    @Override
    public boolean canRegister(){
        return true;
    }

    @Override
    public String getAuthor(){
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getIdentifier(){
        return "simplecurrencies";
    }

    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier){

        if(player == null){
            return "";
        }
            if (Config.checkCurrency(identifier)) {
                return Currencies.getCurrency(identifier, player).toString();
            }

        return null;
    }
}
