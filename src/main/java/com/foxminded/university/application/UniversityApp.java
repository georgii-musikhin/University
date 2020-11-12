package com.foxminded.university.application;


import com.foxminded.university.dao.DAOException;
import com.foxminded.university.dao.StudentDAO;
import com.foxminded.university.domain.University;

public class UniversityApp {
    public static void main(String[] args) throws DAOException {
        StudentDAO dao = new StudentDAO();
        dao.addNewStudentToBase("Popen", "Jopen");
        System.out.println(dao.getStudentById(206));
    }
}