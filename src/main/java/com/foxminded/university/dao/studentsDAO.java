package com.foxminded.university.dao;

import com.foxminded.university.domain.Student;

import java.sql.*;

public class studentsDAO {
    private DAOFactory daoFactory = new DAOFactory();

    public Student createStudent(String firstName, String lastName) throws DAOException {
        String query = "INSERT INTO students (first_name, last_name) VALUES (?, ?);";

        Student student = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.execute();

            resultSet = statement.getGeneratedKeys();
            resultSet.next();

            student = new Student(firstName, lastName);
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

        return student;
    }

}
