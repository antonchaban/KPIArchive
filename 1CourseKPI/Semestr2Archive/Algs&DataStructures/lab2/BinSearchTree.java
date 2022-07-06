package ua.kpi.fict.acts.it03.asdlabs.lab2;

public class BinSearchTree implements BSTInterface {
    Node root;

    @Override
    public void add(int value){
        root = addRecursive(root, value);
    }

    private Node addRecursive(Node current, int value) {
        if (current == null)
            return new Node(value);

        if (value < current.value)
            current.left = addRecursive(current.left, value);
        else if (value > current.value)
            current.right = addRecursive(current.right, value);
        else
            return current;

        return current;
    }

    @Override
    public void print(){
        inOrder(root);
        System.out.println(" ");
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.printf("%s ", node.value);
        inOrder(node.right);
    }

    // ###### пошук елементу за значенням #####

    @Override
    public boolean isExist(int value) {
        return treeSearch(root, value);
    }


    boolean treeSearch(Node root, int key)
    {
        while (root != null) {
            if (key > root.value)
                root = root.right;
            else if (key < root.value)
                root = root.left;
            else
                return true;
        }
        return false;
    }

    // ##### підрахунок суми елементів #####

    @Override
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

    // ##### видалення елементу #####

    @Override
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (value == current.value) {
            if (current.left == null && current.right == null) {
                return null;
            }


            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }


            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }
        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }

    private int findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }
}
