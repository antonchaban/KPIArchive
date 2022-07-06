public class AsdLab5 {
    public static void main(String[] args) {
        int[] testArr = CreateArray();
        int[] firstCopy = testArr.clone();
        int[] arr = CrSortedArr();
        //QuickSort qs = new QuickSort();
        //MergeSort ms = new MergeSort();
        //int arr[] = { 12, 11, 13, 5, 6, 7 };
        ShellSort shs = new ShellSort();
        long m = System.currentTimeMillis();
        shs.sort(testArr);
        System.out.println("ShellSort Time:"+ (double) (System.currentTimeMillis() - m) + "ms");
        HeapSort hs = new HeapSort();
        m = System.currentTimeMillis();
        hs.sort(firstCopy);
        System.out.println("HeapSort Time:"+ (double) (System.currentTimeMillis() - m) + "ms");
        m = System.currentTimeMillis();
        shs.sort(arr);
        System.out.println("ShellSort Sorted Time:"+ (double) (System.currentTimeMillis() - m) + "ms");
        m = System.currentTimeMillis();
        hs.sort(arr);
        System.out.println("HeapSort Sorted Time:"+ (double) (System.currentTimeMillis() - m) + "ms");


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

    static class HeapSort {
        private static void sort(int[] array)
        {
            for (int i = array.length/2 -1;i >= 0; i--)
            {
                heap(array, array.length, i);
            }

            for (int i = array.length-1; i>0; i--)
            {
                int tmp = array[0];
                array[0] = array[i];
                array[i] = tmp;

                heap(array,i,0);
            }
        }

        private static void heap(int[] array, int heapSize, int idx)
        {
            int largest = idx;
            int left = 2*idx + 1;
            int right = 2*idx+2;

            if (left < heapSize && array[left] > array[largest])
                largest = left;

            if (right < heapSize && array[right] > array[largest])
                largest = right;

            if (largest != idx)
            {
                int swap = array[idx];
                array[idx] = array[largest];
                array[largest] = swap;

                heap(array, heapSize, largest);
            }
        }
    }

    static class ShellSort {
        private static void sort(int[] array)
        {
            for (int gap = array.length/2; gap > 0; gap = gap/2)
            {
                for (int i = gap; i < array.length; i++)
                {
                    int tmp = array[i];
                    int j = 0;
                    for (j = i; j >= gap && array[j-gap] > tmp; j = j - gap) {
                        array[j] = array[j - gap];
                    }
                    array[j] = tmp;
                }
            }
        }
    }


    /*static class QuickSort {
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

    }*/


    /*static class MergeSort {
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
    }*/



/*    private static double[] BubbleSort(double[] array)
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
    }*/

/*    private static double[] SelectionSort(double[] array)
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
    }*/

/*    private static int[] InsertionSort(int[] array)
    {
        long m = System.currentTimeMillis();
        for (int i = 1; i < array.length-1; i++)
        {
            for (int j=i-1; j > 0 && array[j-1]>array[j]; j--)
            {
                    int tmp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = tmp;
            }
        }
        System.out.println("Insertion Time:"+ (double) (System.currentTimeMillis() - m) + "ms");

return array;
    }*/

}
