package com.github.carthax08.simplecurrencies.data.db.mysql;

import com.github.carthax08.simplecurrencies.data.PluginPlayer;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler {

    public static boolean yaml = true;
    public static HikariDataSource dataSource;

    public static HikariDataSource connect(String url, String username, String password){
        yaml = false;
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc://" + url);
        config.setUsername(username);
        config.setPassword(password);

        dataSource = new HikariDataSource(config);
        try(Connection con = dataSource.getConnection()){
            con.prepareStatement("CREATE DATABSE [IF NOT EXISTS] SC");
            con.prepareStatement("use SC").execute();
            boolean tableExists = con.prepareStatement("SHOW FULL TABLES WHERE Table_Name = 'Players'").execute();
            PreparedStatement pst = con.prepareStatement("SELECT TABLE_NAME FROM information_schema.TABLES WHERE TABLE_NAME = 'Players'");
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataSource;
    }

    public PluginPlayer getPlayerFromDB(Player player, String tableName){
        try(Connection con = dataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement("SELECT ALL FROM ")
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
