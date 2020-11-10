package com.foxminded.university.domain;

import com.foxminded.university.dao.CourseDAO;
import com.foxminded.university.dao.DAOException;

import java.util.List;
import java.util.stream.Collectors;

public class Course {
    private final CourseDAO courseDao = new CourseDAO();
    private int courseID;
    private String name;
    private String description;

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addListOfStudentsToThisCourse(List<Student> students) throws DomainException {
        try {
            List<Integer> studentIDs = students.stream().map(Student::getStudentID).collect(Collectors.toList());
            courseDao.addStudentsToCourse(studentIDs, courseID);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new DomainException();
        }
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
