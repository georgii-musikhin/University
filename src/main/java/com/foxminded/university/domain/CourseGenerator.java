package com.foxminded.university.domain;

import com.foxminded.university.dao.CourseDAO;
import com.foxminded.university.dao.DAOException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class CourseGenerator {
    private final CourseDAO dao = new CourseDAO();

    public void generateAllCourses() throws DomainException {
        try {
            Map<String, String> courses = getCourses();

            for (Map.Entry<String, String> entry : courses.entrySet()) {
                dao.createCourse(entry.getKey(), entry.getValue());
            }
        } catch (DAOException e) {
            e.printStackTrace();
            throw new DomainException();
        }

    }

    public Map<String, String> getCourses() throws DomainException {
        try {
            Path coursesPath = Paths.get(Objects.requireNonNull(CourseGenerator.class
                    .getClassLoader()
                    .getResource("courses"))
                    .toURI());

            return Files.lines(coursesPath)
                    .collect(
                        Collectors.toMap(
                                k -> k.split("_")[0],
                                v -> v.split("_")[1]
                        )
            );
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            throw new DomainException();
        }
    }
}
