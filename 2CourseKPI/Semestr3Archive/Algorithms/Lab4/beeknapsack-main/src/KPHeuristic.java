import java.util.*;

public class KPHeuristic {


    public Knapsack generateRandomKp(ArrayList<Item> fullItemList) {


        Knapsack newKnapsack = new Knapsack();
        ArrayList<Integer> shuffleList = new ArrayList<>();

        for (int i=0; i<fullItemList.size(); i++) {
            shuffleList.add(i);
        }

        Collections.shuffle(shuffleList);

        for (int x : shuffleList) {
            newKnapsack.addItem(fullItemList.get(x));
        }

        return newKnapsack;
    }


    public ArrayList<Knapsack> generateMultipleRandomKPs(ArrayList<Item> fullItemList, int numOfKnapsacks) throws Exception {

        ArrayList<Knapsack> result = new ArrayList<>();

        while (result.size() != numOfKnapsacks) {
            Knapsack newKnapsack = generateRandomKp(fullItemList);
            if (result.contains(newKnapsack)){
                break;
            }
            result.add(newKnapsack);
        }
        return result;
    }



    public ArrayList<Knapsack> generateRandomNeighbours(ArrayList<Item> fullItemList, Knapsack knapsack, int numOfKnapsacks) {
        ArrayList<Knapsack> knapsacks = new ArrayList<>();
        int knapsackListLength = knapsack.getItemList().size();

        for (int j = 0; j < numOfKnapsacks; j++) {

            Knapsack newKnapsack = new Knapsack();

            if (knapsackListLength > 10 ) {
                for (int i = 0; i < 10; i++) {
                    Item itemToPut = knapsack.getItemList().get(new Random().nextInt(knapsackListLength));
                    newKnapsack.addItem(itemToPut);
                }
            }

            ArrayList<Integer> shuffleList = new ArrayList<>();
            for (int i = 0; i < fullItemList.size(); i++) {
                shuffleList.add(i);
            }
            Collections.shuffle(shuffleList);
            for (int x : shuffleList) {
                newKnapsack.addItem(fullItemList.get(x));
            }

            knapsacks.add(newKnapsack);
        }
        return knapsacks;

    }


}