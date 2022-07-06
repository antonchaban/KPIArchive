package ua.kpi.fict.acts.it03.proga.lab9;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class Main {
  public static void main(String[] args) {
    Laptop[] arr = new Laptop[10];
    arr[0] = new Laptop("Apple","Matrix", 166);
    arr[1] = new Laptop("Asus","Matrix",24);
    arr[2] = new Laptop("Samsung","Matrix",38);
    arr[3] = new Laptop("Acer","Matrix",43);
    arr[4] = new Laptop("MSI","Matrix", 59);
    arr[5] = new Laptop("Lenovo","Matrix", 622);
    arr[6] = new Laptop("Xiaomi","Matrix", 709);
    arr[7] = new Laptop("Huawei","Matrix", 811);
    arr[8] = new Laptop("Toshiba","Matrix", 999);
    arr[9] = new Laptop("HP","Matrix", 1000);

    System.out.println("-------------Comparator 1 (inner static)-----------------");
    ComparatorForLaptop comp1 = new ComparatorForLaptop();
    Arrays.sort(arr, comp1);
    for (Laptop item:arr){
      System.out.println(item.getCompany() + " " + item.getPrice());
    }

    System.out.println("-------------Comparator 2 (anonymous)-----------------");
    ComparatorForLaptop2 comp2 = new ComparatorForLaptop2(){
      @Override
      public int compare(Laptop l1, Laptop l2) {
        if (l1.getPrice() > l2.getPrice())
          return 1;
        else if (l1.getPrice() == l2.getPrice())
          return 0;
        else
          return -1;
      }
    };

    Arrays.sort(arr, comp2);
    for (Laptop item:arr){
      System.out.println(item.getCompany() + " " + item.getPrice());
    }

    System.out.println("-------------Comparator 1 (Treeset)-----------------");
    TreeSet<Laptop> treeset1 = new TreeSet<>(comp1);
    treeset1.addAll(Arrays.asList(arr));
    for (Laptop item:treeset1){
      System.out.println(item.getCompany() + " " + item.getPrice());
    }

    System.out.println("-------------Comparator 2 (Treeset)-----------------");
    TreeSet<Laptop> treeset2 = new TreeSet<Laptop>(comp2);
    for (Laptop item:arr){
      treeset2.add(item);
    }
    for (Laptop item:treeset2){
      System.out.println(item.getCompany() + " " + item.getPrice());
    }
  }

  // Static nested class
  public static class ComparatorForLaptop implements Comparator<Laptop> {
    @Override
    public int compare(Laptop l1, Laptop l2) {
      if(l1.getCompany() != null && l2.getCompany() != null && l1.getCompany().compareTo(l2.getCompany()) != 0) {
        return l1.getCompany().compareTo(l2.getCompany());
      } else {
        return Integer.compare(l1.getPrice(), l2.getPrice());
      }
    }
  }

  // Inner anonymous class
  private abstract static class ComparatorForLaptop2 implements Comparator<Laptop> {
    public abstract int compare(Laptop l1, Laptop p2);
  }
}