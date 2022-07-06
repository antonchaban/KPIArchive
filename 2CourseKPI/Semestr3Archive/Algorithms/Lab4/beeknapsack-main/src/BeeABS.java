import java.util.ArrayList;

public abstract class BeeABS {

    public ArrayList<Double> bestOfRun = new ArrayList<>();
    public ArrayList<Double> meanOfRun = new ArrayList<>();
    public int iterWithBest = 0;

    protected KPHeuristic kph;
    protected Knapsack originalknapsack;
    protected ArrayList<Item> fullItemList;

    int maxIter;
    int[] beesDistribution;
    int sourcesToDump;

    public BeeABS(Knapsack knapsack, ArrayList<Item> fullItemList, KPHeuristic kph, int maxIter, int[] beesDistribution, int sourcesToDump) {
        this.originalknapsack = knapsack;
        this.fullItemList = fullItemList;
        this.kph = kph;
        this.maxIter = maxIter;
        this.beesDistribution = beesDistribution;
        this.sourcesToDump = sourcesToDump;
    }

    public abstract Knapsack run() throws Exception;

}
