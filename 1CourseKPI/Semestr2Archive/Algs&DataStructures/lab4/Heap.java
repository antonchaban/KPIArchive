package ua.kpi.fict.acts.it03.asdlabs.lab4;

import java.util.ArrayList;
import java.util.Collections;

public class Heap {
    public static ArrayList<Integer> items = new ArrayList<Integer>();

    public static void siftUp() {
        int current = items.size() - 1;
        while (current > 0) {
            int parent = (current - 1) / 2;
            if (items.get(parent) < items.get(current)) {
                //swap
                int temp = items.get(current);
                items.set(current, items.get(parent));
                items.set(parent, temp);
            }
            else {
                break;
            }
            //level++
            current = parent;
        }
    }

    public static void siftDown() {
        int current = 0;

        while (current <= items.size() - 1) {
            int leftChild = (2 * current) + 1;
            int rightChild = (2 * current) + 2;
            int locationOfMax = current;

            if ((leftChild <= items.size() - 1) && (rightChild <= items.size() - 1)) {
                if (items.get(rightChild) > items.get(current)) locationOfMax = rightChild;
                if (items.get(leftChild) > items.get(current)) locationOfMax = leftChild;
                if (locationOfMax != current) {
                    //swap
                    int temp = items.get(current);
                    items.set(current, items.get(locationOfMax));
                    items.set(locationOfMax, temp);
                } else {
                    break;
                }
                //level--
                current = locationOfMax;
            }
            else {
                break;
            }
        }

    }

    public int delete() throws Exception {
        if (items.isEmpty() == true) {
            throw new Exception("The Heap is empty");
        }
        if (items.size() == 1) {
            return items.remove(0);
        }
        int current = 0;
        int topValue = items.get(current);
        items.set(current, items.get(items.size() - 1));
        items.remove(items.size() - 1);
        siftDown();
        return topValue;

    }

    public void insert(int item) {
        items.add(item);
        siftUp();
    }


    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public String toString() {
        return items.toString();
    }

    public void heapSort() throws Exception {
        /*int tempVar;
        for (int i = 0; i < items.size()-1; i++)
        {
            for(int j = 0; j < items.size()-i-1; j++)
            {
                if(items.get(j) > items.get(j + 1))
                {
                    tempVar = items.get(j + 1);
                    items.set(j + 1, items.get(j));
                    items.set(j, tempVar);
                }
            }
        }*/
        Collections.sort(items);
    }

    public boolean isExist(int element){
        for (Integer item : items) {
            if (item == element) {
                return true;
            }
        }
        return false;
    }
}
