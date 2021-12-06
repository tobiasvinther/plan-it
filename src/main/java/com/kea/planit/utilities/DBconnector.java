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

    private static Connection connection;

    private DBconnector(){}

    public static Connection getConnection(){
        if(connection != null){
            return connection;
        }

        try(InputStream stream = new FileInputStream("src/main/resources/application.properties")){
            Properties properties = new Properties();
            properties.load(stream);
            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            connection = DriverManager.getConnection(url, username, password);

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
