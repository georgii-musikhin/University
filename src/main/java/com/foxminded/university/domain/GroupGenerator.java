package com.foxminded.university.domain;

import com.foxminded.university.dao.DAOException;
import com.foxminded.university.dao.GroupDAO;
import org.apache.commons.lang3.RandomStringUtils;

public class GroupGenerator {
    private final GroupDAO dao;

    public GroupGenerator() {
        dao = new GroupDAO();
    }

    public GroupGenerator(GroupDAO dao) {
        this.dao = dao;
    }

    public boolean generateGroups() throws DomainException {
        boolean success = false;

        for (int i = 0; i < 10; i++) {
            try {
                dao.addNewGroupInBase(generateName());
                success = true;
            } catch (DAOException e) {
                e.printStackTrace();
                throw new DomainException();
            }
        }

        return success;
    }

    public String generateName() {
        String chars = RandomStringUtils.randomAlphabetic(2).toUpperCase();
        String ints = RandomStringUtils.randomNumeric(2);

        return chars + "-" + ints;
    }
}
