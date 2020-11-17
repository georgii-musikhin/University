package com.foxminded.university.application;

import com.foxminded.university.domain.University;

public class UniversityMenu {
    private final University university;

    public UniversityMenu(University university) {
        this.university = university;
    }

    public UniversityMenu() {
        university = new University();
    }
}
