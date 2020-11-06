package com.foxminded.university.domain;

import com.foxminded.university.dao.DAOException;
import com.foxminded.university.dao.GroupDAO;
import org.apache.commons.lang3.RandomStringUtils;

public class GroupGenerator {
    GroupDAO dao = new GroupDAO();

    public void generateGroups() throws DomainException {
        for (int i = 0; i < 10; i++) {
            try {
                dao.addNewGroupInBase(generateName());
            } catch (DAOException e) {
                e.printStackTrace();
                throw new DomainException();
            }
        }
    }

    public String generateName() {
        String chars = RandomStringUtils.randomAlphabetic(2).toUpperCase();
        String ints = RandomStringUtils.randomNumeric(2);

        return chars + "-" + ints;
    }
}
