# Getting Started 
To get started using SimpleCurrencies, simply put the plugin jar file into your plugins folder. There are no other required plugins, but there are placeholders through PlaceholderAPI.  
  
Once you have done that, start the server. The plugin will generate all the config files for you and put the default values in. If you want to edit the currencies, load config.yml. If you want to edit sell prices or sellable items open prices.yml.  
  
Once you are done editing the config (if you have) do /sc reload

# Default Configs

## config.yml
```yaml # SimpleCurrencies
# Made by CarThax08
# https://www.github.com/carthax08/SimpleCurrencies
# the currencies tab can be edited to your liking, but a few default values are set.
# Keep all currencies lower case to prevent issues.

# If you want to edit the items that can be sold as well as their values, go to prices.yml

currencies:
  - tokens
  - money
```
## prices.yml
```yaml
prices:
  coal:
    price: 0.5
    currency: money
  iron_ore:
    price: 3.0
    currency: money
  gold_ore:
    price: 5.0
    currency: money
  diamond:
    price: 10.0
    currency: money
  emerald:
    price: 20.0
    currency: money
```
# Permissions

* simplecurrencies.admin - /simplecurrencies
* simplecurrencies.getotherbalances - /getcurrency

# Commands

* /simplecurrencies [set, add, remove, clear, reload] [currency] [player] [amount]
* /getcurrency [currency] [player]
* /sendcurrency [currency] [player] [amount]
* /balance [currency]
* /sell (all)

