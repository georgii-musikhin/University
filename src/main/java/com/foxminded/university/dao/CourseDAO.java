package com.foxminded.university.dao;

import com.foxminded.university.domain.Course;

import java.sql.*;

public class CourseDAO {
    private DAOFactory daoFactory = new DAOFactory();

    public Course createCourse(String name, String description) throws DAOException {
        String query = "INSERT INTO courses (course_name, course_description) VALUES (?, ?);";
        Course course;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
             ResultSet resultSet = statement.getGeneratedKeys()) {

            statement.setString(1, name);
            statement.setString(2, description);
            statement.execute();

            resultSet.next();

            course = new Course(name, description);
            return course;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }
}
