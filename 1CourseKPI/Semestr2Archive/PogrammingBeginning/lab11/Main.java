package ua.kpi.fict.acts.it03.proga.lab11;

import java.lang.Math;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        Lambda lambda = (a, b, c, d) -> Math.pow((4 * Math.cosh(Math.sqrt(Math.abs(a / b))) + 3 * Math.asin(c)), d);
        System.out.println("task1");
        task1(1, 2, 1, 4, lambda);

        System.out.println("task2");
        task2();
    }

    public static void task1(double a, double b, double c, double d, Lambda lambda) {
        double result = lambda.calc(a, b, c, d);
        System.out.println("result= " + result);
    }

    public static void task2() {
        Laptop t1 = new Laptop("Apple", "matrix", 20);
        Laptop t2 = new Laptop("Acer", "matrix", 14);
        Laptop t3 = new Laptop("Samsung", "matrix", 38);
        Laptop t4 = new Laptop("Asus", "matrix", 93);
        Iterable<Laptop> list = new LinkedList<>(Arrays.asList(t1, t2, t3, t4));

        list.forEach(iterator -> System.out.println(iterator));
    }


}