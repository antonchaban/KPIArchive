import java.util.Arrays;
import java.util.ArrayList;

public class EightPuzzleState implements State {
    private final int PUZZLE_SIZE = 9;

    private final int[] GOAL = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0};

    private int[] curBoard;

    public EightPuzzleState(int[] board) {
        curBoard = board;
    }

    @Override
    public double findCost() {
        int index = -1;
        int manDist = 0;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                index++;

                int val = (curBoard[index] - 1);

                if (val != -1) {
                    int horiz = val % 3;
                    int vert = val / 3;
                    manDist += Math.abs(vert - (y)) + Math.abs(horiz - (x));
                }
            }
        }
        return manDist;
    }

    private int getHole() {
        int holeIndex = -1;

        for (int i = 0; i < PUZZLE_SIZE; i++) {
            if (curBoard[i] == 0)
                holeIndex = i;
        }
        return holeIndex;
    }


    private int[] copyBoard(int[] state) {
        int[] ret = new int[PUZZLE_SIZE];
        for (int i = 0; i < PUZZLE_SIZE; i++) {
            ret[i] = state[i];
        }
        return ret;
    }

    @Override
    public ArrayList<State> genSuccessors() {
        ArrayList<State> successors = new ArrayList<>();
        int hole = getHole();
        if (hole != 0 && hole != 3 && hole != 6) {
            swapAndStore(hole - 1, hole, successors);
        }
        if (hole != 6 && hole != 7 && hole != 8) {
            swapAndStore(hole + 3, hole, successors);

        }
        if (hole != 0 && hole != 1 && hole != 2) {
            swapAndStore(hole - 3, hole, successors);

        }
        if (hole != 2 && hole != 5 && hole != 8) {
            swapAndStore(hole + 1, hole, successors);

        }
        return successors;
    }

    private void swapAndStore(int d1, int d2, ArrayList<State> s) {
        int[] cpy = copyBoard(curBoard);
        int temp = cpy[d1];
        cpy[d1] = curBoard[d2];
        cpy[d2] = temp;
        s.add((new EightPuzzleState(cpy)));
    }

    @Override
    public boolean isGoal() {
        return Arrays.equals(curBoard, GOAL);
    }

    @Override
    public void printState() {
        System.out.println(curBoard[0] + " | " + curBoard[1] + " | " + curBoard[2]);
        System.out.println("---------");
        System.out.println(curBoard[3] + " | " + curBoard[4] + " | " + curBoard[5]);
        System.out.println("---------");
        System.out.println(curBoard[6] + " | " + curBoard[7] + " | " + curBoard[8]);

    }

    @Override
    public boolean equals(State s) {
        return Arrays.equals(curBoard, ((EightPuzzleState) s).getCurBoard());

    }

    public int[] getCurBoard() {
        return curBoard;
    }
}
