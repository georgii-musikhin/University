package com.foxminded.university.domain;

import com.foxminded.university.dao.DAOException;
import com.foxminded.university.dao.StudentDAO;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.zip.DataFormatException;

import static java.nio.file.Files.readAllLines;

public class StudentGenerator {
    private final StudentDAO dao = new StudentDAO();
    private List<String> firstNames;
    private List<String> lastNames;

    public void generateTwoHundredStudents() throws DomainException {
        getFirstAndLastNames();

        for(int i = 0; i < 200; i++) {
            try {
                dao.addNewStudentToBase(generateName(firstNames, i), generateName(lastNames, i));
            } catch (DAOException e) {
                e.printStackTrace();
                throw new DomainException();
            }
        }
   }

   public void assignTwoHundredStudentsToGroups() throws DomainException {

       for(int i = 1; i <= 200; i++) {
           Random random = new Random(i);
           assignStudentToGroup(i, random.nextInt(10) + 1);
       }
   }

    public void assignTwoHundredStudentsToCourses() throws DomainException {

        for(int i = 1; i <= 200; i++) {
            Random random = new Random(i);
            int courseID = random.nextInt(7) + 1;
            for (int j = random.nextInt(3); j < 3; j++) {
                assignStudentToCourse(i, courseID + j);
            }
        }
    }

   public void createStudent(String firstName, String lastName) throws DomainException {
        try {
            dao.addNewStudentToBase(firstName, lastName);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new DomainException();
        }
   }

   public void assignStudentToGroup(int studentID, int groudID) throws DomainException {
       try {
           dao.setStudentToGroup(studentID, groudID);
       } catch (DAOException e) {
           e.printStackTrace();
           throw new DomainException();
       }
   }

    public void assignStudentToCourse(int studentID, int courseID) throws DomainException {
        try {
            dao.setStudentToCourse(studentID, courseID);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new DomainException();
        }
    }

    public String generateName(List<String> names, int seed) throws DomainException {
        Random random = new Random(seed);
        if(names != null) {
            return  names.get(random.nextInt(names.size()));
        } else {
            throw new DomainException();
        }
    }

    public void getFirstAndLastNames() throws DomainException {
        try {
            Path firstNamesPath = Paths.get(Objects.requireNonNull(StudentGenerator.class
                    .getClassLoader()
                    .getResource("firstNames"))
                    .toURI());
            Path lastNamesPath = Paths.get(Objects.requireNonNull(StudentGenerator.class
                    .getClassLoader()
                    .getResource("lastNames"))
                    .toURI());
            firstNames = new ArrayList<>(readAllLines(firstNamesPath));
            lastNames = new ArrayList<>(readAllLines(lastNamesPath));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            throw new DomainException();
        }
    }
}
