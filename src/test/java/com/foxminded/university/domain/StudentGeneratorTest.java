package com.foxminded.university.domain;

import com.foxminded.university.dao.StudentDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    @Test
    void generateName_ShouldReturnName_ThatMatchGivenName() throws DomainException {
        String expected = "Andrey";

        StudentGenerator generator = new StudentGenerator();
        List<String> names = Collections.singletonList("Andrey");
        String actual = generator.generateName(names, 1);

        assertEquals(expected, actual);
    }

    @Test
    void getFirstAndLastNames_ShouldReturnSuccess_WithRughtFilePath() throws DomainException {
        StudentGenerator generator = new StudentGenerator();

        assertTrue(generator.getFirstAndLastNames());
    }
}