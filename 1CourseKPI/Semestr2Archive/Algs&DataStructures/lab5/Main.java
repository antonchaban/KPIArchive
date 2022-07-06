package ua.kpi.fict.acts.it03.asdlabs.lab5;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        int size = 100000;
        HashTable hashTable = new HashTable(size);
        System.out.println("###TABLE SIZE: " + size);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            hashTable.insert(i,(int)(Math.random() * 100));
        }
        long end = System.currentTimeMillis();
        System.out.println("Hash Table insertion Random 100k " + (end - start) + " ms");
        start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            hashTable.get(i);
        }
        end = System.currentTimeMillis();
        System.out.println("Hash Table isExist 100k " + (end - start) + " ms");
        start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            hashTable.remove(i);
        }
        end = System.currentTimeMillis();
        System.out.println("HashTable delete 100k " + (end - start) + " ms");

        // #####
        System.out.println("######");

        start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            hashTable.insert(i*(int)(Math.random() * 100),(int)(Math.random() * 100));
        }
        end = System.currentTimeMillis();
        System.out.println("Hash Table Random insertion Random 100k " + (end - start) + " ms");
        start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            hashTable.get(i*(int)(Math.random() * 100));
        }
        end = System.currentTimeMillis();
        System.out.println("Hash Table Random isExist 100k " + (end - start) + " ms");
        start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            hashTable.remove(i*(int)(Math.random() * 100));
        }
        end = System.currentTimeMillis();
        System.out.println("HashTable Random delete 100k " + (end - start) + " ms");
    }
}
