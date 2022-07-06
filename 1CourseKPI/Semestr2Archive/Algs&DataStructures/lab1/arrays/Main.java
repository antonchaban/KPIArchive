package ua.kpi.fict.acts.it03.asdlabs.lab1.arrays;

public class Main {
    public static void main(String[] args) {
        MyArrayList list1 = new MyArrayList();
        long tStart = System.currentTimeMillis();
        for (int i = 0; i < 10_000; i++)
        {
            list1.addLast((int) (Math.random() * (1000 - 1)) + 1);
        }
        long tEnd = System.currentTimeMillis();
        long tToDo = tEnd - tStart;
        System.out.println("Arrays adding(10k): " + tToDo + " ms");

        System.out.println("##########");

        tStart = System.currentTimeMillis();
        for (int i = 0; i < 10_000; i++)
        {
            list1.delLast();
        }
        tEnd = System.currentTimeMillis();
        tToDo = tEnd - tStart;
        System.out.println("Arrays Deleting last(10k): " + tToDo + " ms");

        System.out.println("##########");

        tStart = System.currentTimeMillis();
        for (int i = 0; i < 10_000; i++)
        {
            list1.addFirst((int) (Math.random() * (1000 - 1)) + 1);
        }
        tEnd = System.currentTimeMillis();
        tToDo = tEnd - tStart;
        System.out.println("Arrays adding first(10k): " + tToDo + " ms");

        System.out.println("##########");

        tStart = System.currentTimeMillis();
        for (int i = 0; i < 10_000; i++)
        {
            list1.delLast();
        }
        tEnd = System.currentTimeMillis();
        tToDo = tEnd - tStart;
        System.out.println("Arrays del last(10k): " + tToDo + " ms");

        System.out.println("##########");

        tStart = System.currentTimeMillis();
        for (int i = 0; i < 10_000; i++)
        {
            list1.replaceAt((int) (Math.random() * (10_000 - 1)) + 1, (int) (Math.random() * (1000 - 1)) + 1);
        }
        tEnd = System.currentTimeMillis();
        tToDo = tEnd - tStart;
        System.out.println("Rplace at random idx(10k): " + tToDo + " ms");

    }

}
