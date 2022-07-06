import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

public class Main {
    public static void main(String[] args) throws Exception {
        DAO dao = DAO.getInstance();
        dao.setFileName("data.txt");
        dao.readParcel();
        ArrayList<Item> fullItemList = dao.readItems();

        //		System.out.println(new Parcel());
        //		System.out.println(fullItemList.get(1));

        int[] beesDistribution = {300,200,180,160,140,120,110,100,95,90,85, 80, 75, 70, 50, 30, 20, 15}; // length = scouts, число - количество фуражиров на каждого разведчика?
        System.out.println("Scouts: " + beesDistribution.length);
        BeeABS algo1 = new BeeAlgorithm(new Knapsack(), fullItemList, 100, beesDistribution, 15, new KPHeuristic());

        Knapsack p1 = algo1.run();

        /*int check = 0;
        for (int i = 0; i < p1.getItemList().size(); i++){
            System.out.println(p1.getItemList().get(i).getQuality());
            check+=p1.getItemList().get(i).getQuality();
        }
        System.out.println(check);*/


        System.out.println(p1);
        }
    }
