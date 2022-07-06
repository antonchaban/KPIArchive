package com.example.weblab4.models;

import java.util.ArrayList;
import java.util.Iterator;

public class University {
    private ArrayList<Faculty> faculties = new ArrayList<>();
    private final String name;

    public ArrayList<Faculty> getFaculties() {
        return faculties;
    }


    public University(String name, ArrayList<Faculty> faculties) {
        this.faculties = faculties;
        this.name = name;
    }

    public University(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void add(Faculty faculty) {
        if (faculties.contains(faculty)) {
            System.out.println("Ошибка это факультет уже принадлежит институту");
        } else {
            faculties.add(faculty);
        }
    }
    //задание 2)
    public String mostStudents() {
        Faculty answer = null;
        Iterator<Faculty> iterator = faculties.iterator();
        String nullAnswer = "В инстритуте нету факультетов или количество студентов в факультетах равно 0";
        int tempStudents = 0;
        while (iterator.hasNext()) {
            Faculty tempFaculty = iterator.next();
            if (tempFaculty.countStudents() > tempStudents) {
                tempStudents = tempFaculty.countStudents();
                answer = tempFaculty;
            }
        }
        return answer == null ? nullAnswer : "Это факультет: " + answer.getName();
    }

    // Функция  Задание 1)
    public int countStudents() {
        int quantity = 0;
        for (Faculty fac: faculties){
            quantity += fac.countStudents();

        }
        return quantity;
    }


    // Фукнция задание 3)
    private ArrayList<Student> bestStudents() {
        ArrayList<Student> bestStudents = new ArrayList<>();
        Iterator <Faculty> iterator = faculties.iterator();

        while (iterator.hasNext()){
            Faculty fac = iterator.next();
            Iterator <Student> iterator1 = fac.getStudents().iterator();
            while (iterator1.hasNext()){
                Student stud = iterator1.next();
                if (stud.getGPA() >= 95){
                    bestStudents.add(stud);
                }
            }

        }

        return bestStudents;
    }


    //Метод ищет студента по номеру заликовки
    // Тут бы добавить еще проверку на правильность введения номер зачетки
    public void findStudent(String markBookIndex) {

        for (Faculty faculty : faculties) {
            for (Student student : faculty.getStudents()) {
                if (student.getMarkBookIndex().equals(markBookIndex)) {
                    System.out.println("Студент по запросу: " + markBookIndex + " найден. Вот информация:");
                   // student.getDetails();
                    return;
                }
            }
        }
        System.out.println("Студент по запросу:" + markBookIndex + " не найден.");
    }
}
