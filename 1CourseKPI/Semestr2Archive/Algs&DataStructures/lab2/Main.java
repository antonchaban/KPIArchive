package ua.kpi.fict.acts.it03.asdlabs.lab2;

public class Main {
    public static void main(String[] args) {
        BinSearchTree bst = new BinSearchTree();
        long start = System.currentTimeMillis();
        for(int i = 0; i<10_000 ;i++ )
        {
            bst.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Adding 10k: " + (end - start) + " ms");

        System.out.println("#####");

        start = System.currentTimeMillis();
        for(int i = 0; i<10_000 ;i++ )
        {
            bst.delete(i);
        }
        end = System.currentTimeMillis();
        System.out.println("Deleting 10k: " + (end - start) +" ms");
        System.out.println("#####");


        for(int i = 0; i<10_000 ;i++ )
        {
            bst.add(i);
        }

        start = System.currentTimeMillis();
        for(int i = 0; i<10_000 ;i++ )
        {
            bst.isExist(i);
        }
        end = System.currentTimeMillis();
        System.out.println("Existing test: " + (end-start) + " ms");

        System.out.println("#####");

        start = System.currentTimeMillis();
        for(int i = 10000; i<20000 ;i++ )
        {
            bst.isExist(i);
        }
        end = System.currentTimeMillis();
        System.out.println("Existing test(elements which not exist): " + (end-start) + " ms");

        System.out.println("#####");
        for(int i = 0; i<10_000 ;i++ )
        {
            bst.delete(i);
        }

        for(int i = 0; i<8_000 ;i++ )
        {
            bst.add(i);
        }

        start = System.currentTimeMillis();
        bst.getSum();
        end = System.currentTimeMillis();
        System.out.println("Sum of tree elements(8k): " + (end-start) + " ms");
    }
}
