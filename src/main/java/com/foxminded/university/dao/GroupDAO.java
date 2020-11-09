package com.foxminded.university.dao;

import com.foxminded.university.domain.Group;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class GroupDAO {
    private DAOFactory daoFactory = new DAOFactory();

    public Group addNewGroupInBase(String name) throws DAOException {
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

    public Map<Group, Integer> findGroupsWithThisOrLessStudentCount(int studentCount) throws DAOException {
        String query = "SELECT g.group_id, g.group_name, COUNT(s.student_id) as students FROM groups g INNER JOIN students s\n" +
                "ON g.group_id = s.group_id GROUP BY g.group_id HAVING COUNT(s.student_id) <= ?;";

        Map<Group, Integer> groups = new HashMap<>();

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, studentCount);
            statement.execute();

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Group group = new Group(resultSet.getString("group_name"));
                    Integer count = resultSet.getInt("students");

                    groups.put(group, count);
                }
            }

            return  groups;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }
}
