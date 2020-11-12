package com.foxminded.university.dao;

import com.foxminded.university.domain.Student;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentDAOTest {
    private CourseDAO courseDAO = new CourseDAO();
    private StudentDAO studentDAO = new StudentDAO();

    @BeforeEach
    void initDataBase() throws DAOException {
        TablesCreatorDAO tablesCreatorDAO = new TablesCreatorDAO();
        tablesCreatorDAO.createTables();
    }

    @Test
    void addNewStudentToBase_ShouldReturnStudentWirhRightId_WithPredefinedData() throws DAOException {
        Student expected = new Student(3, "Max", "Ushakov");
        Student actual = studentDAO.addNewStudentToBase("Max", "Ushakov");

        assertEquals(expected, actual);
    }

    @Test
    void getStudentById() throws DAOException {
        Student expected = new Student(1, "Mikhail", "Bobrov");
        Student actual = studentDAO.getStudentById(1);

        assertEquals(expected, actual);
    }

    @Test
    void setStudentToGroup_ShouldReturnStudent_WhichWasAddedToBase() throws DAOException {
        Student expected = new Student(3, 2, "Max", "Ushakov");

        studentDAO.addNewStudentToBase("Max", "Ushakov");
        studentDAO.setStudentToGroup(3, 2);
        Student actual = studentDAO.getStudentById(3);

        assertEquals(expected, actual);
    }

    @Test
    void setStudentToCourse_ShouldReturnTrue_ShouldContainStudentOnCourse() throws DAOException {
        Student student = new Student(1, "Mikhail", "Bobrov");
        studentDAO.setStudentToCourse(1, 1);

        List<Student> students = courseDAO.findAllStudentsOnThisCourse("FirstCourse");

        assertTrue(students.contains(student));
    }

    @Test
    void deleteStudentFromBaseById_ShouldReturnException_WhenTrytoGetDeletedStudent() throws DAOException {
        studentDAO.deleteStudentFromBaseById(1);

        assertThrows(DAOException.class, () -> studentDAO.getStudentById(1));
    }
}