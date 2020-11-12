package com.foxminded.university.dao;

import com.foxminded.university.domain.Student;

import java.sql.*;

public class StudentDAO {
    private final DAOFactory daoFactory = new DAOFactory();

    public Student addNewStudentToBase(String firstName, String lastName) throws DAOException {
        String query = "INSERT INTO students (first_name, last_name) VALUES (?, ?);";
        Student student = null;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.executeUpdate();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    student = new Student(resultSet.getInt(1), firstName, lastName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
        return student;
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

    public void setStudentToCourse(int studentID, int courseID) throws DAOException {
        String query = "INSERT INTO students_courses (student_id, course_id) VALUES (?, ?);";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, studentID);
            statement.setInt(2, courseID);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }

    public void deleteStudentFromBaseById(int studentID) throws DAOException {
        String query = "DELETE FROM students WHERE student_id = ?;";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, studentID);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }

    public Student getStudentById(int studentID) throws DAOException {
        String query = "SELECT * FROM students WHERE student_id = ?;";
        Student student = null;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, studentID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    student = new Student(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4));
                }
            }
            if (student != null) {
                return student;
            } else {
                throw new DAOException("Student not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }

    public Student getStudentByFirstName(String firstName) throws DAOException {
        String query = "SELECT * FROM students WHERE first_name = ?;";
        Student student = null;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, firstName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    student = new Student(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4));
                }
            }
            if (student != null) {
                return student;
            } else {
                throw new DAOException("Student not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }
}
