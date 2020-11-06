package com.foxminded.university.domain;

import com.foxminded.university.dao.DAOException;
import com.foxminded.university.dao.TablesCreatorDAO;

public class UniversityCreator {
    private final TablesCreatorDAO tablesCreator = new TablesCreatorDAO();
    private final CourseGenerator courseGenerator = new CourseGenerator();
    private final GroupGenerator groupGenerator = new GroupGenerator();
    private final StudentGenerator studentGenerator = new StudentGenerator();

    public void createUniversity() {
        try {
            tablesCreator.createTables();
            courseGenerator.generateAllCourses();
            groupGenerator.generateGroups();
            studentGenerator.generateTwoHundredStudents();

        } catch (DAOException | DomainException e) {
            e.printStackTrace();
        }
    }
}
