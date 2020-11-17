package com.foxminded.university.application;

import com.foxminded.university.domain.DomainException;
import com.foxminded.university.domain.Student;
import com.foxminded.university.domain.University;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UniversityMenu {
    private final University university;

    public UniversityMenu(University university) {
        this.university = university;
    }

    public UniversityMenu() {
        university = new University();
        university.initializeUniversityData();
    }

    public void outputUnfilledGroups() {
        try (Scanner scanner = new Scanner(System.in)){
            System.out.println("Please, enter the number of students for which the group is not filled: ");
            int studentCount = scanner.nextInt();
            System.out.print("Unfilled group are: ");
            System.out.println(university.findUnfilledGroups(studentCount));
        }
    }

    public void outputAllStudentsInThisCourse() {
        try (Scanner scanner = new Scanner(System.in)){
            System.out.println("Please, enter the name of the course: ");
            String courseName = scanner.next();
            System.out.print("Students on these course are: ");
            System.out.println(university.getAllStudentsInThisCourse(courseName));
        }
    }

    public void createNewStudent() {
        try (Scanner scanner = new Scanner(System.in)){
            System.out.println("Please, enter first name: ");
            String firstName = scanner.next();
            System.out.println("Please, enter last name: ");
            String lastName = scanner.next();
            System.out.print("New student int base: ");
            System.out.println(university.createStudent(firstName, lastName));
        } catch (DomainException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent() {
        try (Scanner scanner = new Scanner(System.in)){
            System.out.println("Please, enter id of student: ");
            int studentID = scanner.nextInt();
            System.out.println(university.deleteStudentById(studentID));
        }
    }

    public void addStudentsToTheCourse() {
        try (Scanner scanner = new Scanner(System.in)){
            List<Student> students = new ArrayList<>();

            System.out.println("Please, enter number of students: ");
            int studentCount = scanner.nextInt();
            for (int i = 0; i < studentCount; i++) {
                System.out.println("Please, enter number id of next student: ");
                int studentID = scanner.nextInt();
                students.add(university.getStudentByID(studentID));
            }

            System.out.println("Please, enter number id of course: ");
            int courseID = scanner.nextInt();

            university.addStudentsToTheCourse(students, courseID);

        } catch (DomainException e) {
            e.printStackTrace();
        }
    }

    public void removeStudentFromCourse() {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("Please, enter id of student: ");
            int studentID = scanner.nextInt();
            System.out.println("Please, enter number id of course: ");
            int courseID = scanner.nextInt();

            university.removeStudentFromCourse(studentID, courseID);
        }
    }
}
