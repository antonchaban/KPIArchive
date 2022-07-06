import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Knapsack implements Comparable<Knapsack> {

    private static double MAX_WEIGHT;
    private double currentWeight = 0;
    private double currentQuality = 0;

    private List<Item> itemList;

    public Knapsack() {
        itemList = new ArrayList<>();
    }

    public Knapsack(List<Item> itemList) {
        this.itemList = itemList;
    }

    public static void setMaxWeight(double maxWeight) {
        MAX_WEIGHT = maxWeight;
    }


    public static double getMaxWeight() {
        return MAX_WEIGHT;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public double getCurrentQuality() {
        return currentQuality;
    }


    public List<Item> getItemList() {
        return itemList;
    }


    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public boolean hasItem(Item item) {
        return itemList.contains(item);
    }


    public boolean addItem(Item item) {
        if (getCurrentWeight() + item.getWeight() > MAX_WEIGHT) return false;
        getItemList().add(item);
        currentWeight += item.getWeight();
        currentQuality += item.getQuality();

        return true;
    }


    public boolean removeItem(Item item) {
        if (itemList.remove(item)) {
            currentWeight -= item.getWeight();
            currentQuality -= item.getQuality();
            return true;
        }
        return false;
    }


    public void removeItems() {
        currentWeight = 0;
        currentQuality = 0;
        itemList.clear();
    }


    @Override
    public String toString() {
        return "Knapsack [MAX_WEIGHT=" + MAX_WEIGHT + ", currentWeight=" + currentWeight + ", currentQuality=" + currentQuality
                + ", itemList=" + itemList + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Knapsack knapsack = (Knapsack) o;

        if (knapsack.hashCode() != hashCode()) return false;

        HashSet<Item> hs = new HashSet<>(knapsack.getItemList());
        HashSet<Item> hs2 = new HashSet<>(getItemList());
        return hs.equals(hs2);
    }

    @Override
    public int hashCode() {
        return itemList != null ? itemList.hashCode() : 0;
    }

    @Override
    public int compareTo(Knapsack o) {
        return (int) (currentQuality - o.getCurrentQuality());
    }

    public int reversedCompareTo(Knapsack o) {
        return (int) (o.getCurrentQuality() - currentQuality);
    }


}