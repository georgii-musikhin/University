package com.foxminded.university.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniversityInitializatorTest {

    @Test
    void initializeUniversityData_ShouldReturnSuccess_WhenInitData() {
        UniversityInitializator initializator = new UniversityInitializator();

        assertTrue(initializator.initializeUniversityData());
    }
}