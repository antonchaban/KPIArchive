import java.util.Arrays;

public class AsdLab3 {
    public static void main(String[] args) {
        int[] testArr = CreateArray();
        int[] firstCopy = testArr.clone();
        int[] arr = CrSortedArr();
        QuickSort qs = new QuickSort();
        MergeSort ms = new MergeSort();
        long m = System.currentTimeMillis();
        qs.quickSort(testArr, 0, testArr.length-1);
        System.out.println("QuickSort Time:"+ (double) (System.currentTimeMillis() - m) + "ms");
        m = System.currentTimeMillis();
        ms.mergeSort(firstCopy, 0, firstCopy.length-1);
        System.out.println("MergeSort Time:"+ (double) (System.currentTimeMillis() - m) + "ms");
        System.out.println(" ");
        m = System.currentTimeMillis();
        qs.quickSort(arr, 0, arr.length-1);
        System.out.println("QuickSort Sorted Time:"+ (double) (System.currentTimeMillis() - m) + "ms");
        m = System.currentTimeMillis();
        ms.mergeSort(arr, 0, arr.length-1);
        System.out.println("MergeSort Sorted Time:"+ (double) (System.currentTimeMillis() - m) + "ms");


    }
    private static void PrintArr(int[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            int x = array[i];
            System.out.print(x + " ");
        }
        System.out.println(" ");
    }

    private static int[] CrSortedArr()
    {
        int n = 1_000_000;
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
        {
            array[i] = i+1;
        }
        return array;
    }

    public static int[] CreateArray()
    {
        int n = 1_000_000;
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
        {
            array[i] = (int) (Math.random() * (1000 - (-1000) + 1) + (-1000));
        }
        return array;
    }


    static class QuickSort {
        private void quickSort(int[] array, int lowest, int highest) {
            if (lowest < highest) {
                int divIdx = partition(array, lowest, highest);
                quickSort(array, lowest, divIdx - 1);
                quickSort(array, divIdx, highest);
            }
        }

        private int partition(int[] array, int lowest, int highest) {
            int rightIdx = highest;
            int leftIdx = lowest;

            int pivot = array[lowest + (highest - lowest) / 2];
            while (leftIdx <= rightIdx) {
                while (array[leftIdx] < pivot) {
                    leftIdx++;
                }

                while (array[rightIdx] > pivot) {
                    rightIdx--;
                }

                if (leftIdx <= rightIdx) {
                    swap(array, rightIdx, leftIdx);
                    leftIdx++;
                    rightIdx--;
                }

            }
            return leftIdx;
        }

        private static void swap(int[] array, int index1, int index2) {
            int tmp  = array[index1];
            array[index1] = array[index2];
            array[index2] = tmp;
        }

    }




    static class MergeSort {
        private void mergeSort(int[] array, int left, int right) {
            if (right > left) {
                int middle = (left + right) / 2;
                mergeSort(array, left, middle);
                mergeSort(array, middle + 1, right);
                Merging(array, left, middle, right);
            }
        }

        private void Merging(int[] array, int left, int middle, int right)
        {
            int sizeL = middle - left + 1;
            int sizeR = right - middle;
            int[] leftArr = new int[sizeL];
            int[] rightArr = new int[sizeR];

            for (int i = 0; i < sizeL; ++i) // заполняем массив левыми элементами (до центрального элемента)
            {
                leftArr[i] = array[left+i];
            }

            for (int j = 0; j < sizeR; ++j) // заполняем массив правыми элементами (теми что после центрального)
            {
                rightArr[j] = array[middle+1+j];
            }

            int i = 0;
            int j = 0;
            int k = left;

            while (i < sizeL && j < sizeR)
            {
                if (leftArr[i] <= rightArr[j])
                {
                    array[k] = leftArr[i];
                    i++;
                }
                else
                {
                    array[k] = rightArr[j];
                    j++;
                }
                k++;
            }

            while (i < sizeL)
            {
                array[k] = leftArr[i];
                i++;
                k++;
            }

            while (j < sizeR)
            {
                array[k] = rightArr[j];
                j++;
                k++;
            }
        }
    }



    private static double[] BubbleSort(double[] array)
    {
        long m = System.nanoTime();
        for (int j = 0; j < array.length-1; j++)
        {
            for (int i = 0; i < array.length-j-1; i++)
            {
                if (array[i] > array[i+1])
                {
                    double swap = array[i];
                    array[i] = array[i+1];
                    array[i+1] = swap;
                }
            }
        }
        System.out.println("Bubble Time:"+ (double) (System.nanoTime() - m));
        return array;
    }

    private static double[] SelectionSort(double[] array)
    {
        long m = System.nanoTime();
        for (int i = 0; i < array.length-1; i++) {
            int minIdx = i;
            for (int j = i; j < array.length - 1; j++) {
                if (array[minIdx] > array[j + 1]) {
                    minIdx = j + 1;
                }
            }
            double swap = array[minIdx];
            array[minIdx] = array[i];
            array[i] = swap;
        }
        System.out.println("Selection Time:"+ (double) (System.nanoTime() - m));
        return array;
    }

private static int[] InsertionSort(int[] array)
    {
        long m = System.currentTimeMillis();
        for (int i = 1; i < array.length; i++)
        {
            int key = array[i];
            int j = i - 1;
            while (j>=0 && array[j] > key)
            {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        System.out.println("Insertion Time:"+ (double) (System.currentTimeMillis() - m) + "ms");

        return array;
    }

}
