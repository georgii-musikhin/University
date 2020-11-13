package com.foxminded.university.dao;

import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CourseDAOTest {
    private CourseDAO courseDAO = new CourseDAO();
    private StudentDAO studentDAO = new StudentDAO();

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

    @Test
    void findAllStudentsOnThisCourse_ShouldReturnSuccess_ThatCourseContainAddedStudent() throws DAOException {
        Student expected = new Student(1, "Mikhail", "Bobrov");
        studentDAO.setStudentToCourse(1, 1);
        List<Student> students = courseDAO.findAllStudentsOnThisCourse("FirstCourse");

        assertTrue(students.contains(expected));
    }

    @Test
    void deleteStudentFromCourse_ShouldReturnSuccess_ThatCourseIsEmptyIfDeletedAllStudents() throws DAOException {
        Student expected = new Student(1, "Mikhail", "Bobrov");
        studentDAO.setStudentToCourse(1, 1);
        courseDAO.deleteStudentFromCourse(1, 1);
        List<Student> students = courseDAO.findAllStudentsOnThisCourse("FirstCourse");

        assertTrue(students.isEmpty());
    }

    @Test
    void addStudentsToCourse_ShouldReturnSuccess_ThatContainAllStudentsAddedToCourse() throws DAOException {
        List<Student> expected = Arrays.asList(
                new Student(1, "Mikhail", "Bobrov"),
                new Student(2, "Gera", "Bodrov")
        );
        courseDAO.addStudentsToCourse(expected.stream().map(Student::getStudentID).collect(Collectors.toList()), 1);
        List<Student> actual = courseDAO.findAllStudentsOnThisCourse("FirstCourse");

        assertEquals(expected, actual);
    }
}