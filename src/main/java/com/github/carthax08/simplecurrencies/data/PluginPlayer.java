package com.github.carthax08.simplecurrencies.data;

import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.HashMap;

public class PluginPlayer {
    public HashMap<String, Double> currencyValueList = new HashMap<>();
    public YamlConfiguration config = null;

    public PluginPlayer(YamlConfiguration config){
        this.config = config;
        for(String key : config.getKeys(false)){
            currencyValueList.put(key, config.getDouble(key));
        }
    }
    public PluginPlayer(ArrayList<String> currencies, ArrayList<Double> values){
        for (String string : currencies){
            currencyValueList.put(string, values.get(currencies.indexOf(string)));
        }
    }

    public boolean addCurrency(String toEdit, Double amount){
        if(currencyValueList.containsKey(toEdit)){
            double newAmount = currencyValueList.get(toEdit) + amount;
            currencyValueList.put(toEdit, newAmount);
            return true;
        }
        return false;
    }
    public boolean setCurrency(String toEdit, Double amount){
        if(currencyValueList.containsKey(toEdit)){;
            currencyValueList.put(toEdit, amount);
            return true;
        }
        return false;
    }
    public boolean removeCurrency(String toEdit, Double amount){
        if(currencyValueList.containsKey(toEdit)){
            double newAmount = currencyValueList.get(toEdit) - amount;
            currencyValueList.put(toEdit, newAmount);
            return true;
        }
        return false;
    }
    public boolean clearCurrency(String toEdit){
        if(currencyValueList.containsKey(toEdit)){
            currencyValueList.put(toEdit, 0d);
            return true;
        }
        return false;
    }
    public double getCurrency(String toEdit){
        if(currencyValueList.containsKey(toEdit)){
            return currencyValueList.get(toEdit);
        }
        return 0;
    }
}