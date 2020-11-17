package com.foxminded.university.domain;

import com.foxminded.university.dao.CourseDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CourseGeneratorTest {

    @Test
    void generateAllCourses_ShouldReturnSuccess_ThanGenerateCourses() throws DomainException {
        CourseDAO courseDAO = Mockito.mock(CourseDAO.class);
        CourseGenerator generator = new CourseGenerator(courseDAO);

        assertTrue(generator.generateAllCourses());
    }

    @Test
    void getCourses_ShouldReturnRightString_WithRightLinkToFile() throws DomainException {
        CourseGenerator generator = new CourseGenerator();

        Map<String, String> expected = new HashMap<>();
        expected.put("Math", "Mathematics includes the study of such topics as quantity, structure, space, and change.");
        expected.put("English", "Lessons to prove your language skills.");
        expected.put("Computer Science", "Computer science is the study of algorithmic processes and computational machines.");
        expected.put("Biology", "Biology is the natural science that studies life and living organisms.");
        expected.put("Chemistry", "Chemistry is the scientific discipline involved with elements and compounds.");
        expected.put("Physics", "Physics is the natural science that studies matter, its motion and behavior through space and time.");
        expected.put("Literature", "Literature is the social science that studies literary works in poetry and prose.");
        expected.put("Economics", "Economics is the social science that studies how people interact with things of value.");
        expected.put("History", "is the social science that studies of past events, particularly in human affairs.");
        expected.put("Geography", "Geography is the social science that studies of places and the relationships between people and their environments.");

        Map<String, String> actual = generator.getCourses();

        assertEquals(expected, actual);
    }
}