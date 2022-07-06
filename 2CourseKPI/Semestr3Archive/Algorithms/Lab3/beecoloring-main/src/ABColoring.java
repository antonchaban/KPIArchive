import java.util.*;
import java.util.stream.IntStream;

public class ABColoring {
    private final Graph graph;
    private final int workers;
    private final int scouts;

    public ABColoring(Graph graph, int workers, int scouts) {
        if (graph == null)
            throw new NullPointerException();

        if (graph.getGraph().length == 0 || graph.getGraph().length != graph.getGraph()[0].length)
            throw new IllegalArgumentException();

        this.graph = graph;
        this.workers = workers;
        this.scouts = scouts;

    }

    public int[] paint() {
        int[] coloredVertices = generateGreedy();
        HashSet<Integer> processedVertices = new HashSet<>();
        int availableBees = workers;

        while (processedVertices.size() != graph.getGraph().length) {
            List<Integer> toProcess = new ArrayList<>();

            for (int i = 0; i < scouts; i++) {
                if (processedVertices.size() == graph.getGraph().length)
                    break;

                int randomVertex = graph.getRandomVertex(processedVertices);
                toProcess.add(randomVertex);
                processedVertices.add(randomVertex);
            }

            toProcess.sort(Comparator.comparingInt(this::getDegree).reversed());

            for (Integer vertex : toProcess) {
                Set<Integer> neighbours = graph.getNeighbours(vertex);

                int processVertices = Math.min(availableBees, neighbours.size());
                int i = 0;

                for (int neighbour : neighbours) {
                    if (i == processVertices)
                        break;

                    List<Integer> colorCount = countColors(coloredVertices);

                    if (trySwapColor(vertex, neighbour, coloredVertices)) {

                        for (int j = 1; j <= colorCount.size(); j++) {

                            if (isColorValid(j, vertex, coloredVertices)
                                    && colorCount.get(coloredVertices[vertex] - 1) < colorCount.get(j - 1)) {
                                coloredVertices[vertex] = j;
                                colorCount = countColors(coloredVertices);
                            }

                            if (isColorValid(j, neighbour, coloredVertices)
                                    && colorCount.get(coloredVertices[neighbour] - 1) < colorCount.get(j - 1)) {
                                coloredVertices[neighbour] = j;
                                colorCount = countColors(coloredVertices);
                            }

                        }
                    }

                    i++;
                }

                availableBees = Math.max(availableBees - neighbours.size(), 0);
            }
        }

        return coloredVertices;
    }

    private List<Integer> countColors(int[] coloredVertices) {
        List<Integer> colorsCount = new ArrayList<>();

        for (int color : coloredVertices) {
            while (colorsCount.size() <= (color - 1)) {
                colorsCount.add(0);
            }

            colorsCount.set(color - 1, colorsCount.get(color - 1) + 1);
        }

        return colorsCount;
    }

    private boolean trySwapColor(int v1, int v2, int[] coloredVertices) {
        if (isColorValidNoConsider(coloredVertices[v1], v2, coloredVertices, v1)
                && isColorValidNoConsider(coloredVertices[v2], v1, coloredVertices, v2)) {
            int tmp = coloredVertices[v1];
            coloredVertices[v1] = coloredVertices[v2];
            coloredVertices[v2] = tmp;
            return true;
        }

        return false;
    }

    private int getDegree(int vertex) {
        return (int) IntStream.range(0, graph.getGraph().length)
                .filter(v -> graph.getGraph()[vertex][v] == 1).count();
    }

    public int[] generateGreedy() {
        int color = 0;
        int[] coloredVertices = new int[graph.getGraph().length];
        HashSet<Integer> completedVertices = new HashSet<>();
        int currentVertex = 0;

        while (completedVertices.size() != graph.getGraph().length) {
            color++;

            doPainting(color, coloredVertices, completedVertices, currentVertex);

            do {
                currentVertex++;
            } while (completedVertices.contains(currentVertex));
        }

        return coloredVertices;
    }

    private void doPainting(int color, int[] coloredVertices, Set<Integer> processedVertices, int currentVertex) {

        if (!isColorValid(color, currentVertex, coloredVertices) || processedVertices.contains(currentVertex))
            return;

        coloredVertices[currentVertex] = color;
        processedVertices.add(currentVertex);

        for (int neighbour : graph.getNeighbours(currentVertex)) {

            for (int neighbourNeighbour : graph.getNeighbours(neighbour)) {

                if (!processedVertices.contains(neighbourNeighbour)) {
                    doPainting(color, coloredVertices, processedVertices, neighbourNeighbour);
                }
            }
        }
    }

    private boolean isColorValidNoConsider(int color, int vertex, int[] coloredVertices, int notConsider) {
        for (int neighbour : graph.getNeighbours(vertex)) {
            if (notConsider == neighbour)
                continue;

            if (coloredVertices[neighbour] == color)
                return false;
        }

        return true;
    }

    private boolean isColorValid(int color, int vertex, int[] coloredVertices) {
        for (int neighbour : graph.getNeighbours(vertex)) {
            if (coloredVertices[neighbour] == color)
                return false;
        }

        return true;
    }


}
