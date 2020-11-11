package com.foxminded.university.dao;

import com.foxminded.university.domain.Student;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentDAOTest {
    private final StudentDAO studentDAO = new StudentDAO();

    @Test
    void addNewStudentToBase() throws DAOException {
        Student expected = new Student(3, "Max", "Ushakov");
        Student actual = studentDAO.addNewStudentToBase("Max", "Ushakov");

        assertEquals(expected, actual);
    }

    @Test
    void setStudentToGroup() {
        Student expected = new Student(3, "Max", "Ushakov");
    }

    @Test
    void setStudentToCourse() {
    }

    @Test
    void deleteStudentFromBaseById() {
    }
}