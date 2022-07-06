package ua.kpi.fict.acts.it03.asdlabs.lab3;

public class Main {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            tree.insert(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("RBTree Adding 1kk: " + (end - start) + " ms");

        System.out.println("#####");

        start = System.currentTimeMillis();
        for(int i = 0; i<1_000_000 ;i++ )
        {
            tree.deleteNode(i);
        }
        end = System.currentTimeMillis();
        System.out.println("RBTree Deleting 1kk: " + (end - start) +" ms");

        System.out.println("#####");

        for(int i = 0; i<1_000_000 ;i++ )
        {
            tree.insert(i);
        }

        start = System.currentTimeMillis();
        for(int i = 0; i<1_000_000 ;i++ )
        {
            tree.searchTree(i);
        }
        end = System.currentTimeMillis();
        System.out.println("RBTree Existing test: " + (end-start) + " ms");

        System.out.println("#####");

        start = System.currentTimeMillis();
        for(int i = 1_000_000; i<2_000_000 ;i++ )
        {
            tree.searchTree(i);
        }
        end = System.currentTimeMillis();
        System.out.println("RBTree Existing test(elements which not exist): " + (end-start) + " ms");

        System.out.println("#####");

        for(int i = 0; i<1_000_000 ;i++ )
        {
            tree.deleteNode(i);
        }

        for(int i = 0; i<1_000_000 ;i++ )
        {
            tree.insert(i);
        }
        start = System.currentTimeMillis();
        tree.getSum();
        end = System.currentTimeMillis();
        System.out.println("RBTree Sum of tree elements(8k): " + (end-start) + " ms");

    }


}
