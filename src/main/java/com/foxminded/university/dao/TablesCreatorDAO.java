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
    private DAOFactory daoFactory = new DAOFactory();

    public void createTables() throws DAOException {
        String query = getQuery();

        try(Connection connection = daoFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
          statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }

    public String getQuery() throws DAOException {
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
}
