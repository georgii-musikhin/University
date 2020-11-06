package com.foxminded.university.dao;

import com.foxminded.university.domain.Student;

import java.sql.*;

public class StudentDAO {
    private DAOFactory daoFactory = new DAOFactory();

    public Student createStudent(String firstName, String lastName) throws DAOException {
        String query = "INSERT INTO students (first_name, last_name) VALUES (?, ?);";
        Student student;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
             ResultSet resultSet = statement.getGeneratedKeys()) {

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.execute();

            resultSet.next();

            student = new Student(firstName, lastName);
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }
}
