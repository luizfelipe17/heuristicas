public class PathVisualization {
    // Fun?$)G!p??o para visualizar a grade e o caminho percorrido
    public static void printGridWithPath(int[][] grid, boolean[][] path) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (path[i][j]) {
                    System.out.print("X "); // Parte do caminho percorrido
                } else if (grid[i][j] == 1) {
                    System.out.print("# "); // Obst??culo
                } else {
                    System.out.print(". "); // C??lula livre
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 0, 1, 0, 0},
            {0, 1, 1, 0, 0},
            {0, 0, 0, 0, 1},
            {1, 0, 1, 0, 0},
            {0, 0, 0, 1, 0}
        };

        // Exemplo de caminho percorrido
        boolean[][] path = {
            {true, true, false, false, false},
            {false, false, false, true, false},
            {false, false, true, true, false},
            {false, false, false, false, false},
            {false, false, false, false, true}
        };

        printGridWithPath(grid, path);
    }
}
