package com.kea.planit.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//Author: Tobias

public class DBconnector {

    private static String url = System.getenv("db.url");
    private static String username = System.getenv("db.username");
    private static String password = System.getenv("db.password");
    private static Connection connection = null;


    private DBconnector(){}

    public static Connection getConnection(){
        if(connection != null){
            return connection;
        }

        try(InputStream stream = new FileInputStream("src/main/resources/application.properties")){
            Properties properties = new Properties();
            properties.load(stream);
            url = properties.getProperty("db.url");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
            connection = DriverManager.getConnection(url, username, password);

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
