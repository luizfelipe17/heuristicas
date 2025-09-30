import java.util.Random;

public class GridGenerator {
    public static int[][] generateGrid(int n, double p) {
        int[][] grid = new int[n][n];
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (rand.nextDouble() > p) {
                    grid[i][j] = 0; // célula livre
                } else {
                    grid[i][j] = 1; // obstáculo
                }
            }
        }
        return grid;
    }

    public static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = 5;
        double p = 0.3; // 30% de obstáculos
        int[][] grid = generateGrid(n, p);
        printGrid(grid);
    }
}
