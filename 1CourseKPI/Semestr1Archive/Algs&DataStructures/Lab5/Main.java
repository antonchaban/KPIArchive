package ua.kpi.fict.acts.it03.asd6;

public class Main {
    public static void main(String[] args)
    {
        //int[] data = { 410, 25, 23, 854, 1, 69, 60};
        int[] data = CreateArray();
        //int[] data = CrSortedArr();
        long m = System.currentTimeMillis();
        RadixSort.radixSort(data, data.length);
        //BucketSort.bucketSort(data, data.length);
        //CountingSort.sort(data, data.length);
        System.out.println("Time elapsed:" + (double)(System.currentTimeMillis() - m) + "ms");
        //PrintArr(data);
    }

    private static void PrintArr(int[] array)
    {
        for (int x : array) {
            System.out.print(x + " ");
        }
        System.out.println(" ");
    }

    private static int[] CrSortedArr()
    {
        int n = 500_000;
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
        {
            array[i] = i+1;
        }
        return array;
    }

    public static int[] CreateArray()
    {
        int n = 500_000;
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
        {
            array[i] = (int) (Math.random() * 10001);
        }
        return array;
    }
}
