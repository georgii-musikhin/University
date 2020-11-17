package com.foxminded.university.domain;

import com.foxminded.university.dao.GroupDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class GroupGeneratorTest {

    @Test
    void generateGroups_ShouldReturnSuccess_IfRightDao() throws DomainException {
        GroupDAO groupDAO = Mockito.mock(GroupDAO.class);
        GroupGenerator generator = new GroupGenerator(groupDAO);

        assertTrue(generator.generateGroups());
    }

    @Test
    void generateName_ShouldReturnSuccess_IfNameMatchPattern() {
        GroupGenerator generator = new GroupGenerator();
        String pattern = "\\w([A-Z])-\\w([0-9])";
        String actual = generator.generateName();

        assertTrue(actual.matches(pattern));
    }
}