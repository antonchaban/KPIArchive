package com.example.weblab4.models;

public class Student {
    private final String name; // ім’я, прізвище
    private final String markBookIndex; // номер залікової книжки
    private double GPA; //середній бал
    private final Faculty faculty;

    public Student(String name, String recordBookIndex, double GPA, Faculty faculty) {
        this.name = name;
        this.markBookIndex = recordBookIndex;
        this.GPA = GPA;
        this.faculty = faculty;
        faculty.add(this);
    }


    public String getName() {
        return name;
    }

    public double getGPA() {
        return GPA;
    }

    public String getMarkBookIndex() {
        return markBookIndex;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public Faculty getFaculty() {
        return faculty;
    }
}
