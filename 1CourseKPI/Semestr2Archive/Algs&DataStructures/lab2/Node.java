package ua.kpi.fict.acts.it03.asdlabs.lab2;

public class Node {
    int value;
    Node left;
    Node right;

    Node(Integer value) {
        this.value = value;
        right = null;
        left = null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
