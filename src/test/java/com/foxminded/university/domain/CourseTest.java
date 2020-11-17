package com.foxminded.university.domain;

import com.foxminded.university.dao.CourseDAO;
import com.foxminded.university.dao.DAOException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CourseTest {

    @Test
    void addListOfStudentsToThisCourse_WithRightListOfStudents_ShouldReturnSuccess() throws DomainException {
        CourseDAO courseDAO = Mockito.mock(CourseDAO.class);
        Course course = new Course(courseDAO, 1, "FirstCourse", "This is a first course");

        List<Student> students = Arrays.asList(
                new Student(1, "Mikhail", "Bobrov"),
                new Student(2, "Gera", "Bodrov")
        );


        assertEquals(true, course.addListOfStudentsToThisCourse(students));
    }
}