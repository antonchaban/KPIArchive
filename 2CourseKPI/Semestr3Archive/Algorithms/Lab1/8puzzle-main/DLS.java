import java.util.Stack;

public class DLS {
    public int process(int[][] startState, int[][] finalState, int maxDepth) {
        Node startNode = new Node(startState, 0);
        Stack<Node> stack = new Stack<>();
        stack.push(startNode);
        int counter = 0;
        int stackCounter = 0;

        while (!stack.isEmpty()) {
            counter++;
            Node node = stack.pop();
            if (checkFinal(node, finalState)) {
                System.out.println("Done " + counter);
                System.out.println("States " + stackCounter);
                return node.getDepth();
            }
            if (node.getDepth() < maxDepth) {
                stackCounter += fillStack(node, stack);
            }

        }

        System.out.println(counter);
        return -1;
    }

    private int fillStack(Node node, Stack<Node> stack) {
        int counter = 0;
        int[][] state = node.getState();
        int[][] newState;
        int newDepth = node.getDepth() + 1;
        Node newNode;
        int row = 0;
        int col = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == 0) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }
        if (row < 2) {
            newState = cloneArr(state);
            newState[row][col] = newState[row + 1][col];
            newState[row + 1][col] = 0;
            newNode = new Node(newState, newDepth);
            stack.push(newNode);
            counter++;
        }
        if (row > 0) {
            newState = cloneArr(state);
            newState[row][col] = newState[row - 1][col];
            newState[row - 1][col] = 0;
            newNode = new Node(newState, newDepth);
            stack.push(newNode);
            counter++;
        }
        if (col < 2) {
            newState = cloneArr(state);
            newState[row][col] = newState[row][col + 1];
            newState[row][col + 1] = 0;
            newNode = new Node(newState, newDepth);
            stack.push(newNode);
            counter++;
        }
        if (col > 0) {
            newState = cloneArr(state);
            newState[row][col] = newState[row][col - 1];
            newState[row][col - 1] = 0;
            newNode = new Node(newState, newDepth);
            stack.push(newNode);
            counter++;
        }

        return counter;
    }

    private int[][] cloneArr(int[][] arr) {
        int[][] dest = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                dest[i][j] = arr[i][j];
            }
        }
        return dest;
    }

    private boolean checkFinal(Node node, int[][] finalState) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (node.getState()[i][j] != finalState[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private class Node {

        private int[][] state;
        private int depth;


        public Node(int[][] state, int depth) {
            this.state = state;
            this.depth = depth;

        }

        public int[][] getState() {
            return state;
        }


        public int getDepth() {
            return depth;
        }

    }

}
