package com.foxminded.university.dao;

import com.foxminded.university.domain.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GroupDAOTest {

    private GroupDAO groupDAO = new GroupDAO();

    @BeforeEach
    void setUp() throws DAOException {
        TablesCreatorDAO tablesCreatorDAO = new TablesCreatorDAO();
        tablesCreatorDAO.createTables();
    }

    @Test
    void getGroupById() throws DAOException {
        Group expected = new Group(1, "AS-49");
        Group actual = groupDAO.getGroupById(1);

        assertEquals(expected, actual);
    }

    @Test
    void addNewGroupInBase() throws DAOException {
        Group expected = new Group(3, "AS-32");
        Group actual = groupDAO.addNewGroupInBase("AS-32");

        assertEquals(expected, actual);
    }

    @Test
    void findGroupsWithThisOrLessStudentCount() throws DAOException {
        Group expected = new Group(1, "AS-49");
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.setStudentToGroup(1, 1);
        Map<Group, Integer> groups = groupDAO.findGroupsWithThisOrLessStudentCount(1);

        assertTrue(groups.containsKey(expected));
    }
}