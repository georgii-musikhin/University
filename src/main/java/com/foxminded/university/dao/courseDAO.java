package com.foxminded.university.dao;

import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Group;

import java.sql.*;

public class courseDAO {
    private DAOFactory daoFactory = new DAOFactory();

    public Course createCourse(String name, String description) throws DAOException {
        String query = "INSERT INTO courses (course_name, course_description) VALUES (?, ?);";

        Course course = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
            connection = daoFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, name);
            statement.setString(2, description);
            statement.execute();

            resultSet = statement.getGeneratedKeys();
            resultSet.next();

            course = new Course(name, description);
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

        return course;
    }
}
