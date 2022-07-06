package ua.kpi.fict.acts.it03.asd6;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

final class BucketSort {
    static void bucketSort(int[] intArr, int noOfBuckets)
    {
        List<Integer>[] buckets = new List[noOfBuckets];
        for(int i = 0; i < noOfBuckets; i++)
        {
            buckets[i] = new LinkedList<>();
        }

        for(int num : intArr){
            //System.out.println("hash- " + hash(num));
            buckets[hash(num)].add(num);
        }

        for(List<Integer> bucket : buckets){
            //InsertionSort(bucket);
            //sort(bucket);
            Collections.sort(bucket);
        }

        int i = 0;
        for(List<Integer> bucket : buckets){
            for(int num : bucket){
                intArr[i++] = num;
            }
        }

    }

    private static int hash(int num){
        return num/10;
    }

    private static List<Integer> InsertionSort(List<Integer> list)
    {
        for (int j = 1; j < list.size(); j++) {
            Integer current = list.get(j);
            int i = j-1;
            while ((i > -1) && ((list.get(i).compareTo(current)) == 1)) {
                list.set(i+1, list.get(i));
                i--;
            }
            list.set(i+1, current);
        }
        return list;
    }

    public static void sort(List<Integer> list) {
        sort(list, 0, list.size() - 1);
    }

    public static void sort(List<Integer> list, int from, int to) {
        if (from < to) {
            int pivot = from + (to - from)/2;
            int left = from + 1;
            int right = to;
            int pivotValue = list.get(pivot);
            while (left <= right) {
                while (left <= to && pivotValue >= list.get(left)) {
                    left++;
                }
                while (right > from && pivotValue < list.get(right)) {
                    right--;
                }
                if (left < right) {
                    Collections.swap(list, left, right);
                }
            }
            Collections.swap(list, pivot, left - 1);
            sort(list, from, right - 1);
            sort(list, right + 1, to);
        }
    }

}
