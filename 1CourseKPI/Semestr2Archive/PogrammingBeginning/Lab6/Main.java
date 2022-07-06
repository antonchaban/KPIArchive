package ua.kpi.fict.acts.it03.proga.Lab6;

public class Main {
    public static void main(String[] args) {
        ByFormula test1 = new ByFormula();
        test1.calc(1_000_000_000);

        OneThread test2 = new OneThread();
        test2.calc(1_000_000_000);

        MultiThreads test3 = new MultiThreads();
        test3.calc(1_000_000_000);

    }

}
