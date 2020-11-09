package com.foxminded.university.application;

import com.foxminded.university.dao.CourseDAO;
import com.foxminded.university.dao.DAOException;
import com.foxminded.university.domain.UniversityCreator;

public class UniversityApp {
    public static void main(String[] args) throws DAOException {
        CourseDAO dao = new CourseDAO();

        dao.deleteStudentFromCourse(1, 7);
    }
}