package ua.kpi.fict.acts.it03.asdlabs.lab3;

public class AvlTree {
    Node root = null;

    public static void main(String[] args) {
        AvlTree tree = new AvlTree();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            tree.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("AVL Tree Adding 1kk: " + (end - start) + " ms");
        System.out.println("#####");

        start = System.currentTimeMillis();
        for(int i = 0; i<1_000_000 ;i++ )
        {
            tree.remove(i);
        }
        end = System.currentTimeMillis();
        System.out.println("AVL Tree Deleting 1kk: " + (end - start) +" ms");

        System.out.println("#####");

        for(int i = 0; i<1_000_000 ;i++ )
        {
            tree.add(i);
        }

        start = System.currentTimeMillis();
        for(int i = 0; i<1_000_000 ;i++ )
        {
            tree.contains(i);
        }
        end = System.currentTimeMillis();
        System.out.println("AVL Tree Existing test: " + (end-start) + " ms");

        System.out.println("#####");

        start = System.currentTimeMillis();
        for(int i = 1_000_000; i<2_000_000 ;i++ )
        {
            tree.contains(i);
        }
        end = System.currentTimeMillis();
        System.out.println("AVL Tree Existing test(elements which not exist): " + (end-start) + " ms");

        System.out.println("#####");

        for(int i = 0; i<1_000_000 ;i++ )
        {
            tree.remove(i);
        }

        for(int i = 0; i<1_000_000 ;i++ )
        {
            tree.add(i);
        }
        start = System.currentTimeMillis();
        tree.getSum();
        end = System.currentTimeMillis();
        System.out.println("AVL Tree Sum of tree elements(1kk): " + (end-start) + " ms");

    }

    public void getSum()
    {
        System.out.println("Sum of tree: " + sumCounter(root));
    }

    private long sumCounter(Node node)
    {
        long counter;
        if (node == null) return 0;
        counter = sumCounter(node.left) + node.value + sumCounter(node.right);
        return counter;
    }

    private void resetHeight(Node n) {
        n.height = Math.max(getHeight(n.left), getHeight(n.right)) + 1;
    }

    private int getHeight(Node n) {
        return n == null ? -1 : n.height;
    }


    public boolean contains(Integer k) {
        return contains(root, k);
    }

    private boolean contains(Node n, Integer k) {
        if (n == null)
            return false;
        int cmp = k.compareTo(n.key);
        if (cmp < 0)
            return contains(n.left, k);
        else if (cmp > 0)
            return contains(n.right, k);
        return true;
    }

    public void remove(int k) {
        root = remove(root, k);
    }

    private Node remove(Node n, Integer k) {
        if (n == null)
            return n;
        int cmp = k.compareTo(n.key);
        if (cmp < 0)
            n.left = remove(n.left, k);
        else if (cmp > 0)
            n.right = remove(n.right, k);
        else {
            if (n.left == null) {
                n = n.right;
                return n;
            } else if (n.right == null) {
                n = n.left;
                return n;
            } else {
                Node replace = minV(n.right);
                n.key = replace.key;
                n.value = replace.value;
                n.right = remove(n.right, n.key);
                return n;
            }
        }
        return balance(n);
    }

    private Node minV(Node n) {
        while (n.left != null)
            n = n.left;
        return n;
    }

    public void add(int k, int v) {
        root = add(root, k, v);
    }

    public void add(int k) {
        root = add(root, k, k);
    }

    private Node add(Node n, Integer k, Integer v) {
        if (n == null)
            return new Node(k, v);
        int cmp = k.compareTo(n.key);
        if (cmp < 0)
            n.left = add(n.left, k, v);
        else if (cmp > 0)
            n.right = add(n.right, k, v);
        else
            n.value = v;
        return balance(n);
    }

    private Node balance(Node n) {
        resetHeight(n);
        int diff1 = getHeight(n.left) - getHeight(n.right);
        if (diff1 >= 2) {
            int diff2 = getHeight(n.left.right) - getHeight(n.left.left);
            // поворот ліворуч
            if (diff2 > 0)
                n.left = rotateLeft(n.left);
            n = rotateRight(n);
        }
        // поворот ліворуч
        else if (diff1 <= -2) {
            int diff2 = getHeight(n.right.left) - getHeight(n.right.right);
            // поворот праворуч
            if (diff2 > 0)
                n.right = rotateRight(n.right);
            n = rotateLeft(n);
        }
        return n;
    }

    // поворот ліворуч
    private Node rotateLeft(Node n) {
        Node x = n.right;
        n.right = x.left;
        x.left = n;
        resetHeight(n);
        resetHeight(x);
        return x;
    }

    // поворот праворуч
    private Node rotateRight(Node n) {
        Node x = n.left;
        n.left = x.right;
        x.right = n;
        resetHeight(n);
        resetHeight(x);
        return x;
    }

    // вкладений клас
    static class Node {
        Integer key, value, height;
        Node left, right;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.height = 0;
        }

        Node(int key) {
            this.key = key;
            this.value = key;
            this.height = 0;
        }
    }
}
