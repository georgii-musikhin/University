package com.foxminded.university.domain;

import com.foxminded.university.dao.StudentDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class StudentGeneratorTest {

    @Test
    void generateTwoHundredStudents_ShouldReturnSuccess_IfRightDao() throws DomainException {
        StudentDAO studentDAO = Mockito.mock(StudentDAO.class);
        StudentGenerator generator = new StudentGenerator(studentDAO);

        assertTrue(generator.generateTwoHundredStudents());
    }

    @Test
    void assignTwoHundredStudentsToGroups_ShouldReturnSuccess_IfRightDao() throws DomainException {
        StudentDAO studentDAO = Mockito.mock(StudentDAO.class);
        StudentGenerator generator = new StudentGenerator(studentDAO);

        assertTrue(generator.assignTwoHundredStudentsToGroups());
    }

    @Test
    void assignTwoHundredStudentsToCourses_ShouldReturnSuccess_IfRightDao() throws DomainException {
        StudentDAO studentDAO = Mockito.mock(StudentDAO.class);
        StudentGenerator generator = new StudentGenerator(studentDAO);

        assertTrue(generator.assignTwoHundredStudentsToCourses());
    }
}