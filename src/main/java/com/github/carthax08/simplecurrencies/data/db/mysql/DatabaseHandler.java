package com.github.carthax08.simplecurrencies.data.db.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseHandler {

    public static boolean yaml = true;

    public static HikariDataSource connect(String url, String username, String password){
        yaml = false;
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc://" + url);
        config.setUsername(username);
        config.setPassword(password);

        return new HikariDataSource(config);
    }

}
