package ua.kpi.fict.acts.it03.asdlabs.lab4;

public class Main {
    public static void main(String[] args) throws Exception {
        Heap heap = new Heap();
        Heap heap1 = new Heap();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            heap.insert((int) (Math.random() * 100));
        }
        long end = System.currentTimeMillis();
        System.out.println("BinaryHeap insertion Random 100k " + (end - start) + " ms");
        start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            heap.isExist((int) (Math.random() * 100));
        }
        end = System.currentTimeMillis();
        System.out.println("BinaryHeap isExist Random 100k " + (end - start) + " ms");
        start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            heap.delete();
        }
        end = System.currentTimeMillis();
        System.out.println("BinaryHeap delete Random 100k " + (end - start) + " ms");

        // ############
        start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            heap.insert(i);
        }
        end = System.currentTimeMillis();
        System.out.println("BinaryHeap insertion sorted 100k " + (end - start) + " ms");
        start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            heap.isExist(i);
        }
        end = System.currentTimeMillis();
        System.out.println("BinaryHeap isExist sorted 100k " + (end - start) + " ms");
        start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            heap.delete();
        }
        end = System.currentTimeMillis();
        System.out.println("BinaryHeap delete sorted 100k " + (end - start) + " ms");
    }
}
