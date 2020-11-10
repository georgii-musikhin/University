package com.foxminded.university.dao;

import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Student> findAllStudentsOnThisCourse(String courseName) throws DAOException {
        String query = "SELECT s.student_id, s.group_id, s.first_name, s.last_name FROM courses c INNER JOIN students_courses sc\n" +
                "ON c.course_id = sc.course_id INNER JOIN students s\n" +
                "ON s.student_id = sc.student_id\n" +
                "WHERE c.course_name = ?;";
        List<Student> students = new ArrayList<>();

        try (Connection connection = daoFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, courseName);
            statement.execute();

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Student student =
                            new Student(resultSet.getString("first_name"), resultSet.getString("last_name"));
                            student.setGroupID(resultSet.getInt("group_id"));
                    students.add(student);
                }
            }

            return  students;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }

    public void deleteStudentFromCourse(int studentID, int courseID) throws DAOException {
        String query = "DELETE FROM students_courses WHERE student_id = ? AND course_id = ?;";

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

    public void addStudentsToCourse(List<Integer> studentIDs, int courseID) throws DAOException {
        String query = "INSERT INTO students_courses VALUES (?, ?);";

        try(Connection connection = daoFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            for(int id : studentIDs) {
                statement.setInt(1, id);
                statement.setInt(2, courseID);
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }
}
