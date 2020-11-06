package com.foxminded.university.dao;

import com.foxminded.university.domain.Student;

import java.sql.*;

public class StudentDAO {
    private DAOFactory daoFactory = new DAOFactory();

    public Student addNewStudentToBase(String firstName, String lastName) throws DAOException {
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

    public void setStudentToGroup(int studentID, int groupID) throws DAOException {
        String query = "UPDATE students SET group_id = ? WHERE student_id = ?;";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, groupID);
            statement.setInt(2, studentID);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }
}
