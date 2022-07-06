public class Main {
    public static void main(String[] args) {
        /*int[][] puzzle = {{7, 1, 5},
                {2, 3, 4},
                {0, 8, 6}};*/

        /*int[][] puzzle = {  {0,2,6},
                {3,7,1},
                {5,4,8}};*/

        /*int[][] solution = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}};*/

        //IDS ids = new IDS();
        //System.out.println(ids.execute(puzzle, solution));
        //DLS dls = new DLS();
       // System.out.println(dls.process(puzzle, solution, 20));
        //int[] puzzle1d = {5,1,7,3,0,2,4,8,6};
       int[] puzzle1d = {0,2,6,3,7,1,5,4,8};
        RBFS rbfs = new RBFS();
        rbfs.search(puzzle1d);

    }
}
