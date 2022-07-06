import java.util.AbstractCollection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

public class Graph {
    private final int[][] graph;
    private final Random random;

    public int[][] getGraph() {
        return graph;
    }

    public Random getRandom() {
        return random;
    }

    public Graph(int[][] graph) {
        this.graph = graph;
        this.random = new Random();
    }

    public Set<Integer> getNeighbours(int vertex) {

        return IntStream.range(0, graph.length)
                .filter(v -> graph[vertex][v] == 1)
                .collect(HashSet::new, HashSet::add, AbstractCollection::addAll);
    }

    public int getRandomVertex(Set<Integer> processedVertices) {
        int[] availableVertices = IntStream.range(0, graph.length)
                .filter(vertex -> !processedVertices.contains(vertex)).toArray();

        return availableVertices[random.nextInt(availableVertices.length)];
    }
}
