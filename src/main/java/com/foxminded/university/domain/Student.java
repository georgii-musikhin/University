package com.foxminded.university.domain;

import com.foxminded.university.dao.CourseDAO;
import com.foxminded.university.dao.DAOException;
import com.foxminded.university.dao.StudentDAO;

import java.util.Objects;

public class Student {
    private int studentID;
    private int groupID;
    private String firstName;
    private String lastName;
    private final StudentDAO studentDAO = new StudentDAO();
    private final CourseDAO courseDAO = new CourseDAO();

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student(int studentID, String firstName, String lastName) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void deleteStudent() throws DomainException {
        try {
            studentDAO.deleteStudentFromBaseById(studentID);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new DomainException();
        }
    }

    public void assignStudentToGroup(int groudID) throws DomainException {
        try {
            studentDAO.setStudentToGroup(studentID, groudID);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new DomainException();
        }
    }

    public void assignStudentToCourse(int courseID) throws DomainException {
        try {
            studentDAO.setStudentToCourse(studentID, courseID);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new DomainException();
        }
    }

    public void deleteStudentFromCourseById(int courseID) throws DomainException {
        try {
            courseDAO.deleteStudentFromCourse(studentID, courseID);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new DomainException();
        }
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return studentID == student.studentID &&
                groupID == student.groupID &&
                Objects.equals(firstName, student.firstName) &&
                Objects.equals(lastName, student.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, groupID, firstName, lastName);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", groupID=" + groupID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
