package ua.kpi.fict.acts.it03.asd7;

class HeapSort { //O(count*logN)
    static void sort(int[] array, int count)
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
            if (i <= array.length - count)
                break;
            heap(array,i,0);
        }
    }

    private static void heap(int[] array, int heapSize, int idx)
    {
        int largest = idx;
        int left = 2*idx + 1;
        int right = 2*idx+2;

        if (left < heapSize && array[left] < array[largest])
            largest = left;

        if (right < heapSize && array[right] < array[largest])
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