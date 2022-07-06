public class IDS {

    public String execute(int[][] puzzle, int[][] solution) {
        if (isDone(puzzle)) {
            return "Already Done";
        } else {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                DLS dls = new DLS();
                int result = dls.process(puzzle, solution, i);
                if (result != -1) {
                    String strRes = "Result is: " + result;
                    return strRes;
                }
            }

        }
        return "Solution Not Found";
    }

    private boolean isDone(int[][] puzzle) {
        int[][] solution = {{1, 2, 3},
                            {4, 5, 6},
                            {7, 8, 0}};
        if (solution.equals(puzzle)) {
            return true;
        }
        return false;

    }
}
