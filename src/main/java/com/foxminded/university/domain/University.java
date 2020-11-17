package com.foxminded.university.domain;

import com.foxminded.university.dao.CourseDAO;
import com.foxminded.university.dao.DAOException;
import com.foxminded.university.dao.GroupDAO;
import com.foxminded.university.dao.StudentDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class University {

    private final CourseDAO courseDAO;
    private final StudentDAO studentDAO;
    private final GroupDAO groupDAO;

    public University(CourseDAO courseDAO, StudentDAO studentDAO, GroupDAO groupDAO) {
        this.courseDAO = courseDAO;
        this.studentDAO = studentDAO;
        this.groupDAO = groupDAO;
    }

    public University() {
        this.courseDAO = new CourseDAO();
        this.studentDAO = new StudentDAO();
        this.groupDAO = new GroupDAO();
    }

    public boolean initializeUniversityData() {
        UniversityInitializator initializator = new UniversityInitializator();
        return initializator.initializeUniversityData();
    }

    public Map<Group, Integer> findUnfilledGroups(int studentCount) {
        Map<Group, Integer> groups = new HashMap<>();

        try {
            groups = groupDAO.findGroupsWithThisOrLessStudentCount(studentCount);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return groups;
    }

    public List<Student> getAllStudentsInThisCourse(String courseName) {
        List<Student> students = new ArrayList<>();

        try {
            students = courseDAO.findAllStudentsOnThisCourse(courseName);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return students;
    }

    public Student createStudent(String firstName, String lastName) throws DomainException {
        Student student = null;

        try {
            student = studentDAO.addNewStudentToBase(firstName, lastName);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        if (student != null) {
            return student;
        } else  {
            throw new DomainException();
        }
    }

    public boolean deleteStudentById(int studentID) {
        boolean success = false;

        try {
            studentDAO.deleteStudentFromBaseById(studentID);
            success = true;
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean addStudentsToTheCourse(List<Student> students, int courseID) {
        boolean success = false;

        try {
            List<Integer> studentIDs = students.stream().map(Student::getStudentID).collect(Collectors.toList());
            courseDAO.addStudentsToCourse(studentIDs, courseID);
            success = true;
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean removeStudentFromCourse(int studentID, int courseID) {
        boolean success = false;

        try {
            courseDAO.deleteStudentFromCourse(studentID, courseID);
            success = true;
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return success;
    }

    public Student getStudentByID(int studentID) throws DomainException {
        try {
            return studentDAO.getStudentById(studentID);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new DomainException();
        }
    }
}
