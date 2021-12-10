package com.kea.planit.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Author: Tobias

public class DBconnector {

    private static Connection connection;

    private DBconnector(){}

    public static Connection getConnection(){
        if(connection != null){
            return connection;
        }

        try {
            String url = System.getenv("db.url");
            String username = System.getenv("db.username");
            String password = System.getenv("db.password");
            connection = DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            System.out.println("Something went wrong trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }
}
