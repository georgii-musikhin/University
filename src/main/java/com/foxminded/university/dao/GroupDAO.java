package com.foxminded.university.dao;

import com.foxminded.university.domain.Group;

import java.sql.*;

public class GroupDAO {
    private DAOFactory daoFactory = new DAOFactory();

    public Group createGroup(String name) throws DAOException {
        String query = "INSERT INTO groups (group_name) VALUES (?);";
        Group group;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
             ResultSet resultSet = statement.getGeneratedKeys()) {

            statement.setString(1, name);
            statement.execute();

            resultSet.next();

            group = new Group(name);
            return group;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }
}
