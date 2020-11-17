package com.foxminded.university.domain;

import com.foxminded.university.dao.DAOException;
import com.foxminded.university.dao.TablesCreatorDAO;

public class UniversityInitializator {

    private TablesCreatorDAO tablesCreator;
    private final CourseGenerator courseGenerator;
    private final GroupGenerator groupGenerator;
    private final StudentGenerator studentGenerator;

    public UniversityInitializator() {
        tablesCreator = new TablesCreatorDAO();
        courseGenerator = new CourseGenerator();
        groupGenerator = new GroupGenerator();
        studentGenerator = new StudentGenerator();
    }

    public UniversityInitializator(TablesCreatorDAO tablesCreator) {
        this();
        this.tablesCreator = tablesCreator;
    }

    public boolean initializeUniversityData() {
        boolean success = false;

        try {
            tablesCreator.createTables();

            courseGenerator.generateAllCourses();
            groupGenerator.generateGroups();
            studentGenerator.generateTwoHundredStudents();

            studentGenerator.assignTwoHundredStudentsToGroups();

            tablesCreator.createStudentCoursesTable();
            studentGenerator.assignTwoHundredStudentsToCourses();
            success = true;
        } catch (DAOException | DomainException e) {
            e.printStackTrace();
        }

        return success;
    }
}
