package com.foxminded.university.application;


import com.foxminded.university.domain.University;

import java.util.Scanner;

public class UniversityApp {
    public static void main(String[] args) {
        University university = new University();
        university.initializeUniversityData();

        UniversityMenu menu = new UniversityMenu(university);

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to the unevrsity database application! \n" +
                    "Here you can: \n" +
                    "Add new student! (Command code - 0)\n" +
                    "Delete student by his ID (Command code - 1)\n" +
                    "Find unfilled groups (Command code - 2)\n" +
                    "Find find all students at this course (Command code - 3)\n" +
                    "Add student list to the course (Command code - 4)\n" +
                    "Delete student from his course (Command code - 5)\n");
            int comandCode = scanner.nextInt();

            switch (comandCode) {
                case (0) -> menu.createNewStudent();
                case (1) -> menu.deleteStudent();
                case (2) -> menu.outputUnfilledGroups();
                case (3) -> menu.outputAllStudentsInThisCourse();
                case (4) -> menu.addStudentsToTheCourse();
                case (5) -> menu.removeStudentFromCourse();
                default -> throw new IllegalArgumentException("Illegal command code!");
            }
        }
    }
}