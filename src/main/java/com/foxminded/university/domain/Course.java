package com.foxminded.university.domain;

import com.foxminded.university.dao.CourseDAO;
import com.foxminded.university.dao.DAOException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Course {
    private final CourseDAO courseDao;
    private int courseID;
    private String name;
    private String description;

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
        courseDao = new CourseDAO();
    }

    public Course(int courseID, String name, String description) {
        this.courseID = courseID;
        this.name = name;
        this.description = description;
        courseDao = new CourseDAO();
    }

    public Course(CourseDAO courseDao, int courseID, String name, String description) {
        this.courseDao = courseDao;
        this.courseID = courseID;
        this.name = name;
        this.description = description;
    }

    public boolean addListOfStudentsToThisCourse(List<Student> students) throws DomainException {
        boolean success = false;

        try {
            List<Integer> studentIDs = students.stream().map(Student::getStudentID).collect(Collectors.toList());
            courseDao.addStudentsToCourse(studentIDs, courseID);
            success = true;
        } catch (DAOException e) {
            e.printStackTrace();
            throw new DomainException();
        }

        return success;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return courseID == course.courseID &&
                Objects.equals(name, course.name) &&
                Objects.equals(description, course.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseID, name, description);
    }
}
