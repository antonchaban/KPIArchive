package ua.kpi.fict.acts.it03.asd7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("==========Task1==========");
        task1(12);
        System.out.println("==========Task2==========");
        task2(24599);
        System.out.println("==========Task3==========");
        task3(11);
        System.out.println("=========================");



    }

    private static void task1(int gifts)
    {
        int[] giftsPrices = new int[100];
        final int min = 5000;
        final int max = 6999;
        for (int i = 0; i < giftsPrices.length; i++)
        {
            giftsPrices[i] = randIntInRange(min,max);
        }
        int[] firstCopy = giftsPrices.clone();
        long m = System.currentTimeMillis();
        System.out.println("Bills required: " + greedy(gifts,giftsPrices));
        System.out.println("Time elapsed greedy: " + (double)(System.currentTimeMillis() - m) + "ms");
        System.out.println("/////////DYNAMIC/////////");
        m = System.currentTimeMillis();
        System.out.println(dinGreedy(gifts,firstCopy));
        System.out.println("Time elapsed dynamic: " + (double)(System.currentTimeMillis() - m) + "ms");
    }

    private static void task2(int money) {
        int[] giftsPrices = new int[25];
        final int min = 7000;
        final int max = 8999;
        for (int i = 0; i < giftsPrices.length; i++) {
            giftsPrices[i] = randIntInRange(min, max);
        }
        System.out.println("You can purchase: " + maxGifts(money, giftsPrices) +" gifts " + " for: " + money);
    }

    private static void task3(int gifts) {
        int[] giftsPrices = new int[25];
        final int min = 9000;
        final int max = 10_000;
        for (int i = 0; i < giftsPrices.length; i++) {
            giftsPrices[i] = randIntInRange(min, max);
        }
        System.out.println("Min sum required: " + minCheapest(gifts, giftsPrices));
    }

    private static int greedy(int gifts, int[] mediumGiftsPrices) {
        int[] bills = {500, 200, 100, 50, 20, 10, 5, 2, 1};
        int reqMoney = minCheapest(gifts, mediumGiftsPrices);
        int counter = 0;
        System.out.println("Money Required: " + reqMoney);
        for(int x : bills)
        {
            counter += reqMoney / x;
            reqMoney %= x;
        }
        return counter;
    }

    private static int maxGifts(int money, int[] giftsPrice) {
        List<Integer> giftList = new ArrayList<>(giftsPrice.length);
        for (int i : giftsPrice)
        {
            giftList.add(i);
        }
        int giftCounter = 0;
        Collections.sort(giftList);
        if (money < Collections.min(giftList))
            return 0;
        System.out.println("Money: " + money + " List: " + giftList);
        do {
            money = money - Collections.min(giftList);
            giftCounter++;
            giftList.remove(Collections.min(giftList));
            System.out.println("Counter: "+giftCounter + " Money: " + money);
            //System.out.println(giftList);
        } while (money > Collections.min(giftList));
        return giftCounter;
    }

    private static int minCheapest(int gifts, int[] prices)
    {
        HeapSort hs = new HeapSort();
        int reqMoney = 0;
        hs.sort(prices, gifts);
       // System.out.println("Prices: " + Arrays.toString(prices));
        for (int i = prices.length-1; i >= prices.length-gifts; i--)
            reqMoney += prices[i];
        return reqMoney;
    }

    private static int dinGreedy(int gifts, int[] mediumGiftsPrices)
    {
        int money = minCheapest(gifts, mediumGiftsPrices);
        //int[] bills = {2, 5};
        int[] bills = {500, 200, 100, 50, 20, 10, 5, 2, 1};
        int[] mem = new int[money+1];
        int memRes;
        for (int i = 1; i < money+1; i++)
            mem[i] = Integer.MAX_VALUE;
        for (int i = 1; i < money + 1; i++) // O(money)
        {
            for (int j = 0; j < bills.length; j++)
            {
                if (bills[j]<=i) {
                    memRes = mem[i - bills[j]];
                    if (memRes != Integer.MAX_VALUE) {
                        mem[i] = Math.min(memRes + 1, mem[i]);
                    }
                }
            }
        }
        return mem[money];
    }

    public static int randIntInRange(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
