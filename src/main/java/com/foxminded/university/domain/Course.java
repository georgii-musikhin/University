package com.foxminded.university.domain;

public class Course {
    private int courseID;
    private String name;
    private String description;

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
