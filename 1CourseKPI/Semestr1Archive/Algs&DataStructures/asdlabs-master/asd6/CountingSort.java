package ua.kpi.fict.acts.it03.asd6;

final class CountingSort {
     static void sort(int[] array, int length)
     {
        int max = array[0];
        for (int i = 1; i < length; i++)
        {
            if (array[i] > max)
                max = array[i];
        }
        int[] count = new int[max + 1];

     /*   for (int i = 0; i < max; ++i) {
            count[i] = 0;
        }*/

        for (int i = 0; i < length; i++)
        {
            count[array[i]]++;
        }

        for (int i = 1; i <= max; i++)
        {
            count[i] += count[i - 1];
        }

        int[] output = new int[length + 1];

        for (int i = length - 1; i >= 0; i--)
        {
            output[count[array[i]] - 1] = array[i];
            count[array[i]]--;
        }

        for (int i = 0; i < length; i++)
        {
            array[i] = output[i];
        }
    }
}
