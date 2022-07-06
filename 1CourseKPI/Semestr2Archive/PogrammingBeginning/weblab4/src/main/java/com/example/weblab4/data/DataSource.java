package com.example.weblab4.data;

import com.example.weblab4.models.*;

import java.util.ArrayList;

public class DataSource {
    public static ArrayList<University> uniks = new ArrayList<>();
    static {
        University KPI = new University("KPI");
        University KNEU = new University("KNEU");
        Faculty FICT = new Faculty("FICT", KPI);
        Faculty FMM = new Faculty("FMM", KPI);
        Student emad = new Student("Hamad Emad", "QWER1234", 9.6, FICT);
        Student anton = new Student("Chaban Anton", "ASDF5678", 99.7, FMM);
        Student nickita = new Student("Nick Phil", "ZXCV9012", 99.8, FICT);
        uniks.add(KPI);
        uniks.add(KNEU);
    }

    public static ArrayList<Faculty> getUnivFaculties() {
        ArrayList<Faculty> allFaculties = new ArrayList<>();
        for (University university: uniks) {
            allFaculties.addAll(university.getFaculties());
        }
        return allFaculties;
    }

    public static ArrayList<Student> allStudents() {
        ArrayList<Student> allStudents = new ArrayList<>();
        for (Faculty faculty: getUnivFaculties()) {
            allStudents.addAll(faculty.getStudents());
        }
        return allStudents;
    }
}
