package ua.kpi.fict.acts.it03.proga.lab13;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {


        LinkedList<Enrollee> abitura = formAbitura(new LinkedList<>());
        University univer = formUniver(new University("KPI"));

        task1(abitura, 4);
        System.out.println("---");
        task2(univer);
        System.out.println("---");
        task3(abitura);


    }

    public static LinkedList<Enrollee> formAbitura(LinkedList<Enrollee> abitura) {
        abitura.add(new Enrollee("Ivan", 20));
        abitura.add(new Enrollee("Anton", 40));
        abitura.add(new Enrollee("Emad", 57));
        abitura.add(new Enrollee("Petrov", 60));
        abitura.add(new Enrollee("Sidor", 65));
        abitura.add(new Enrollee("Name1", 75));
        abitura.add(new Enrollee("Name2", 80));
        abitura.add(new Enrollee("Name3", 95));
        abitura.add(new Enrollee("Name4", 100));

        return abitura;
    }

    public static University formUniver(University univer) {

        Faculty faculty1 = new Faculty("FICT");
        Faculty faculty2 = new Faculty("IASA");

        Student student1 = new Student("Ilia", "Illich", 1234, 99);
        Student student2 = new Student("Vlad", "Vladosovich", 4321, 64);
        Student student3 = new Student("Ivan", "Ivanov", 9876, 85);
        Student student4 = new Student("Petro", "Petrov", 6789, 45);

        univer.getList().add(faculty1);
        univer.getList().add(faculty2);

        faculty1.getList().add(student1);
        faculty1.getList().add(student2);
        faculty2.getList().add(student3);
        faculty2.getList().add(student4);

        return univer;
    }

    public static void task1(LinkedList<Enrollee> list, int limit) {
        list.stream()
                .filter(x -> x.getScore() > 60)
                .sorted(Comparator.comparingInt(Enrollee::getScore))
                .limit(limit)
                .sorted(Comparator.comparing(Enrollee::getName))
                .forEach(System.out::println);
    }

    public static void task3(LinkedList<Enrollee> list) {
        List<String> names = list.stream()
                .map(Enrollee::getName)
                .peek(System.out::println)
                .collect(Collectors.toList());
    }

    public static void task2(University university) {
        Comparator<Student> comp = Comparator.comparing(Student::getSurname)
                .thenComparing(Student::getName).thenComparing(Student::getRBN);

        List<Student> sortedSrudents = university.getList().stream()
                .flatMap(x -> x.getList().stream())
                .sorted(comp)
                .peek(System.out::println)
                .collect(Collectors.toList());
    }
}