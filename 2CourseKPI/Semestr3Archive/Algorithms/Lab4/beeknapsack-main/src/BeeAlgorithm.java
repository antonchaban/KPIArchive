import java.util.ArrayList;

public class BeeAlgorithm extends BeeABS {


    public BeeAlgorithm(Knapsack knapsack, ArrayList<Item> fullItemList, int maxIter, int[] beesDistribution, int sourcesToDump, KPHeuristic kph) {
        super(knapsack, fullItemList, kph, maxIter, beesDistribution, sourcesToDump);
    }


    public Knapsack run() throws Exception {

        if (sourcesToDump > beesDistribution.length) throw new Exception("MoreDumpedSourcesThanScouts");

        /*bestOfRun.clear();
        meanOfRun.clear();*/

        int currentIter;
        ArrayList<Knapsack> currentSources = kph.generateMultipleRandomKPs(fullItemList, sourcesToDump);

        for(currentIter = 0 ; currentIter < maxIter ; currentIter++ )
        {
            System.out.println("" + (currentIter+1) + ": ");

            currentSources.sort(Knapsack::reversedCompareTo);

            for (int i = 0; i < sourcesToDump; ++i) {
                currentSources.set(i, sendBees(currentSources.get(i), beesDistribution[i]));
            }


            currentSources.sort(Knapsack::reversedCompareTo);
            currentSources.forEach(knapsack -> System.out.print("" + (double)Math.round(knapsack.getCurrentQuality() * 100) / 100 + " "));
            System.out.println();

            bestOfRun.add(currentSources.get(0).getCurrentQuality());

            //
            meanOfRun.add(currentSources.stream()
                    .mapToDouble(Knapsack::getCurrentQuality)
                    .average()
                    .getAsDouble());
        }

        currentSources.sort(Knapsack::reversedCompareTo);
        return currentSources.get(0);

    }

    private Knapsack sendBees(Knapsack source, int numOfBeesToSend){

        ArrayList<Knapsack> neighbours = new ArrayList<>(kph.generateRandomNeighbours(fullItemList, source, numOfBeesToSend));
        Knapsack best = neighbours.stream().max(Knapsack::compareTo).get();
        if (best.compareTo(source) > 0) return best;
        return source;
    }

}


