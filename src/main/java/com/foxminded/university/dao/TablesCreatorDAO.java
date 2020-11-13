package com.foxminded.university.dao;

import com.foxminded.university.domain.CourseGenerator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Objects;
import java.util.stream.Collectors;

public class TablesCreatorDAO {
    private final DAOFactory daoFactory = new DAOFactory();

    public void createTables() throws DAOException {
        String query = getCreationTablesQuery();

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }

    public String getCreationTablesQuery() throws DAOException {
        try {
            Path queryPath = Paths.get(Objects.requireNonNull(CourseGenerator.class
                    .getClassLoader()
                    .getResource("createTables.sql"))
                    .toURI());
            return Files.lines(queryPath)
                    .collect(Collectors.joining(""));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }

    public void createStudentCoursesTable() throws DAOException {
        String query = "DROP TABLE IF EXISTS students_courses CASCADE;\n" +
                "CREATE TABLE students_courses (\n" +
                "  student_id INTEGER REFERENCES students (student_id) ON DELETE CASCADE,\n" +
                "  course_id  INTEGER REFERENCES courses (course_id) ON DELETE CASCADE,\n" +
                "  PRIMARY KEY (student_id, course_id));";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }
}
