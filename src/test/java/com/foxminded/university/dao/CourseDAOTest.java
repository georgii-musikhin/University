package com.foxminded.university.dao;

import com.foxminded.university.domain.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseDAOTest {
    private CourseDAO courseDAO = new CourseDAO();

    @BeforeEach
    void setUp() throws DAOException {
        TablesCreatorDAO tablesCreatorDAO = new TablesCreatorDAO();
        tablesCreatorDAO.createTables();
    }

    @Test
    void createCourse_ShouldReturnSuccess_WhenAddRightCourse() throws DAOException {
        Course expected = new Course(3, "ThirdCourse", "This is third Course");
        Course actual = courseDAO.createCourse("ThirdCourse", "This is third Course");

        assertEquals(expected, actual);
    }
}