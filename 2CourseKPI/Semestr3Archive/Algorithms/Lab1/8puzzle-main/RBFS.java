import java.util.ArrayList;
import java.util.Stack;

public class RBFS {
    public static int counter = 0;

    public static int getCounter() {
        return counter;
    }

    public void search(int[] board) {
        SearchNode root = new SearchNode(new EightPuzzleState(board));
        //Stack<SearchNode> s = new Stack<>();
        //s.add(root);

        //SearchNode tempNode = s.pop();
        rbfs(root, Integer.MAX_VALUE); //tempNode -> root
    }

    private double rbfs(SearchNode node, double fLimit) {
        if (node.getCurState().isGoal()) {
            success(node);
        }

        ArrayList<State> tempSuccessors = node.getCurState().genSuccessors();
        /*for (int i = 0; i < tempSuccessors.size(); i++){
            tempSuccessors.get(i).printState();
            System.out.println(" ");
        }*/
        ArrayList<SearchNode> nodeSuccessors = new ArrayList<>();

        for (int i = 0; i < tempSuccessors.size(); i++) {
            State tempSuccessor = tempSuccessors.get(i);
            SearchNode checkedNode;

            checkedNode = new SearchNode(node, tempSuccessor, node.countParents(), ((EightPuzzleState) tempSuccessor).findCost());


            if (!checkRepeats(checkedNode)) {
                nodeSuccessors.add(checkedNode);
                counter++;
            }
        }


        if (nodeSuccessors.size() == 0) {
            return Integer.MAX_VALUE;
        }

        nodeSuccessors.sort((o1, o2) -> (int) (o1.getFCost() - o2.getFCost()));
        /*System.out.println("###########");
        nodeSuccessors.get(0).getCurState().printState();
        System.out.println(" ");*/
        SearchNode lowestNode = nodeSuccessors.get(0);

        while (lowestNode.getFCost() <= fLimit && lowestNode.getFCost() < Integer.MAX_VALUE) {
            double newFLimit;
            if (nodeSuccessors.size() > 1) {
                SearchNode alternative = nodeSuccessors.get(1);
                newFLimit = Math.min(fLimit, alternative.getFCost());
            } else {
                newFLimit = fLimit;
            }
            double newFCost = rbfs(lowestNode, newFLimit);
            lowestNode.setFCost(newFCost);

            nodeSuccessors.sort((o1, o2) -> (int) (o1.getFCost() - o2.getFCost()));
            lowestNode = nodeSuccessors.get(0);
        }

        return lowestNode.getFCost();
    }

    private static boolean checkRepeats(SearchNode n) {
        boolean retValue = false;
        SearchNode checkNode = n;
        while (n.getParent() != null && !retValue) {
            if (n.getParent().getCurState().equals(checkNode.getCurState())) {
                retValue = true;
            }
            n = n.getParent();
        }

        return retValue;
    }



    private static void success(SearchNode node) {
        Stack<SearchNode> solutionPath = new Stack<>();
        solutionPath.push(node);
        node = node.getParent();

        while (node.getParent() != null) {
            solutionPath.push(node);
            node = node.getParent();
        }
        solutionPath.push(node);

        int loopSize = solutionPath.size();
        int depth = solutionPath.size()-1;

        for (int i = 0; i < loopSize; i++) {
            node = solutionPath.pop();
            node.getCurState().printState();
            System.out.println();
            System.out.println();
        }
        System.out.println("The cost was: " + node.getCost());
        System.out.println("Depth is: " + depth);
        System.out.println("States: " + getCounter());

        System.exit(0);
    }

    private class SearchNode {
        private State curState;
        private SearchNode parent;
        private double cost; // parents counter
        private double hCost; // man dist
        private double fCost;

        public int countParents(){
            SearchNode node = this;
            int counter = 0;
            while (node.getParent() != null) {
                node = node.getParent();
                counter++;
            }
            return  counter;
        }

        public SearchNode(State s) {
            curState = s;
            parent = null;
            cost = 0;
            hCost = 0;
            fCost = 0;
        }

        public SearchNode(SearchNode prev, State s, double c, double h) {
            parent = prev;
            curState = s;
            cost = c;
            hCost = h;
            fCost = cost + hCost;
        }


        public State getCurState() {
            return curState;
        }

        public SearchNode getParent() {
            return parent;
        }


        public double getCost() {
            return cost;
        }


        public double getHCost() {
            return hCost;
        }

        public double getFCost() {
            return fCost;
        }

        public void setFCost(double fCost) {
            this.fCost = fCost;
        }
    }
}
