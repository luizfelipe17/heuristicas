public class SubidaEncosta {

    public static boolean[][] hillClimb(int[][] grid, int[] start, int[] goal) {
        int n = grid.length;
        boolean[][] path = new boolean[n][n];
        int x = start[0];
        int y = start[1];
        path[x][y] = true;

        while (true) {
            int bestX = -1, bestY = -1;
            int bestDist = DistanceCalculator.manhattanDistance(x, y, goal[0], goal[1]);
            
            int[][] moves = {{-1,0},{1,0},{0,-1},{0,1}};
            for (int[] m : moves) {
                int nx = x + m[0];
                int ny = y + m[1];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == 0 && !path[nx][ny]) {
                    int dist = DistanceCalculator.manhattanDistance(nx, ny, goal[0], goal[1]);
                    if (dist < bestDist) {
                        bestDist = dist;
                        bestX = nx;
                        bestY = ny;
                    }
                }
            }

            if (bestX == -1 || bestY == -1) break;
            x = bestX;
            y = bestY;
            path[x][y] = true;

            if (x == goal[0] && y == goal[1]) break;
        }
        return path;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] grid = GridGenerator.generateGrid(n, 0.3);
        int[] start = {0, 0};
        int[] goal = {n-1, n-1};

        System.out.println("Grade inicial (0 = livre, 1 = obst?culo):");
        GridGenerator.printGrid(grid);

        boolean[][] path = hillClimb(grid, start, goal);

        System.out.println("\nResultado 'Subida de Encosta':");
        PathVisualization.printGridWithPath(grid, path);
    }
}
