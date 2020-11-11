package com.foxminded.university.application;


import com.foxminded.university.domain.University;

public class UniversityApp {
    public static void main(String[] args) {
        University university = new University();

        university.initializeUniversityData();
    }
}