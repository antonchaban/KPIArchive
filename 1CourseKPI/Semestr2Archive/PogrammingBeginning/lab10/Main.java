package ua.kpi.fict.acts.it03.proga.lab10;

import java.util.Comparator;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        task1();
    }


    public static void task1() {
        Device m1 = new Laptop("mod1", "type1", 170);
        Device m2 = new Laptop("mod2", "type2", 120);
        Device m3 = new Laptop("mod3", "type3", 156);
        Device m4 = new Laptop("mod4", "type4", 123);

        System.out.println("By price");

        Comparator<Device> comp = (a, b) -> {
            return Integer.compare(a.getPrice(), b.getPrice());
        };
        TreeSet<Device> trSet = new TreeSet<>(comp);
        trSet.add(m1);
        trSet.add(m2);
        trSet.add(m3);
        trSet.add(m4);
        System.out.println(trSet);

        System.out.println("By reversed price");

        TreeSet<Device> trSet2 = new TreeSet<>(comp.reversed());
        trSet2.add(m1);
        trSet2.add(m2);
        trSet2.add(m3);
        trSet2.add(m4);
        System.out.println(trSet2);

        System.out.println("By Company and price");

        Comparator<Device> comp2 = (a, b) -> (a.company.compareTo(b.company));

        TreeSet<Device> trSet3 = new TreeSet<>(comp.thenComparing(comp2));
        trSet3.add(m1);
        trSet3.add(m2);
        trSet3.add(m3);
        trSet3.add(m4);
        System.out.println(trSet3);

        System.out.println("By price, null first");

        TreeSet<Device> trSet4 = new TreeSet<>(Comparator.nullsFirst(comp));
        trSet4.add(m1);
        trSet4.add(m2);
        trSet4.add(m3);
        trSet4.add(m4);
        trSet4.add(null);
        System.out.println(trSet4);

    }
}