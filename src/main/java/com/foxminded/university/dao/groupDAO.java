package com.foxminded.university.dao;

import com.foxminded.university.domain.Group;

import java.sql.*;

public class groupDAO {
    private DAOFactory daoFactory = new DAOFactory();

    public Group createGroup(String name) throws DAOException {
        String query = "INSERT INTO groups (group_name) VALUES (?);";

        Group group = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
            connection = daoFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, name);
            statement.execute();

            resultSet = statement.getGeneratedKeys();
            resultSet.next();

            group = new Group(name);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
                if(statement != null) {
                    statement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return group;
    }
}
