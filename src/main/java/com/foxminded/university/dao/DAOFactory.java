package com.foxminded.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOFactory {
    private String url = "jdbc:postgresql://localhost/postgres";
    private String user = "postgres";
    private String password = "admin";

    public Connection getConnection() throws DAOException, SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException();
        }

    }
}
