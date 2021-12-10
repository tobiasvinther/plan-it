package com.kea.planit.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//Author: Tobias Vinther

public class DBconnector {

    private static String url = System.getenv("db.url");
    private static String user = System.getenv("db.user");
    private static String password = System.getenv("db.password");
    private static Connection connection = null;

    private DBconnector(){}

    public static Connection getConnection(){
        if(connection != null){
            return connection;
        }

        try {
            System.out.println("Trying to connect...");
            connection = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            System.out.println("Something went wrong trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection getConnection_old(){
        if(connection != null){
            return connection;
        }

        try(InputStream stream = new FileInputStream("src/main/resources/application.properties")){
            Properties properties = new Properties();
            properties.load(stream);
            url = properties.getProperty("db.url");
            user = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
            connection = DriverManager.getConnection(url, user, password);

        } catch (SQLException  | IOException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
