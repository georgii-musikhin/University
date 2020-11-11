package com.foxminded.university.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {

    public Connection getConnection() throws DAOException, SQLException {
        try {
            Properties properties = new Properties();
            InputStream inputStream = getClass().getResourceAsStream("/dbConnection.properties");
            properties.load(inputStream);

            String drivers = properties.getProperty("jdbc.driverClassName");
            String connectionURL = properties.getProperty("jdbc.url");
            String username = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");

            Class.forName(drivers);

            return DriverManager.getConnection(connectionURL, username, password);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }
}
