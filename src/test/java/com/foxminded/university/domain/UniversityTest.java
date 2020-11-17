package com.foxminded.university.domain;

import com.foxminded.university.dao.CourseDAO;
import com.foxminded.university.dao.DAOException;
import com.foxminded.university.dao.GroupDAO;
import com.foxminded.university.dao.StudentDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UniversityTest {

    @Test
    void findUnfilledGroups_ShouldReturnRightMap_WintRigthDAO() throws DAOException {
        GroupDAO groupDAO = Mockito.mock(GroupDAO.class);
        University university = new University(new CourseDAO(), new StudentDAO(), groupDAO);
        Map<Group, Integer> groups = new HashMap<>();
        groups.put((new Group("AS-12")),18);
        groups.put((new Group("FS-14")), 19);

        when(groupDAO.findGroupsWithThisOrLessStudentCount(20))
                .thenReturn(groups);

        assertEquals(groups, university.findUnfilledGroups(20));
    }

    @Test
    void getAllStudentsInThisCourse_ShouldReturnRightMap_WintRigthDAO() throws DAOException {
        CourseDAO courseDAO = Mockito.mock(CourseDAO.class);
        University university = new University(courseDAO, new StudentDAO(), new GroupDAO());
        List<Student> students = new ArrayList<>() {{
            new Student("Michael", "Johnson");
            new Student("Asd", "Gas");
        }};

        when(courseDAO.findAllStudentsOnThisCourse("Math"))
                .thenReturn(students);

        assertEquals(students, university.getAllStudentsInThisCourse("Math"));
    }

    @Test
    void createStudent_ShouldReturnRightStudent_WithRightDAO() throws DomainException, DAOException {
        StudentDAO studentDAO = Mockito.mock(StudentDAO.class);
        University university = new University(new CourseDAO(), studentDAO, new GroupDAO());
        Student student = new Student(1, "Aaa", "Bbb");

        when(studentDAO.addNewStudentToBase("Aaa", "Bbb"))
                .thenReturn(student);

        assertEquals(student, university.createStudent("Aaa", "Bbb"));
    }

    @Test
    void deleteStudentById_SholdReturnTrue_WithRightDAO() {
        StudentDAO studentDAO = Mockito.mock(StudentDAO.class);
        University university = new University(new CourseDAO(), studentDAO, new GroupDAO());

        assertTrue(university.deleteStudentById(2));
    }

    @Test
    void addStudentsToTheCourse_SholdReturnTrue_WithRightDAO() {
        CourseDAO courseDAO = Mockito.mock(CourseDAO.class);
        University university = new University(courseDAO, new StudentDAO(), new GroupDAO());
        List<Student> students = new ArrayList<>() {{
            new Student("Michael", "Johnson");
            new Student("Asd", "Gas");
        }};

        assertTrue(university.addStudentsToTheCourse(students, 4));

    }

    @Test
    void removeStudentFromCourse_ShouldReturnTrue_WithRightDAO() {
        CourseDAO courseDAO = Mockito.mock(CourseDAO.class);
        University university = new University(courseDAO, new StudentDAO(), new GroupDAO());

        assertTrue(university.removeStudentFromCourse(200, 3));
    }

    @Test
    void initializeUniversityData() {
        StudentDAO studentDAO = Mockito.mock(StudentDAO.class);
        GroupDAO groupDAO = Mockito.mock(GroupDAO.class);
        CourseDAO courseDAO = Mockito.mock(CourseDAO.class);
        University university = new University(courseDAO, studentDAO, groupDAO);

        assertTrue(university.initializeUniversityData());
    }
}