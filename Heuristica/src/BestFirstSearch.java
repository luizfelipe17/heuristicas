import java.util.*;

public class BestFirstSearch {

    static class Node {
        int x, y, g, h; // g = dist?ncia percorrida, h = heur?stica
        Node parent;

        Node(int x, int y, int g, int h, Node parent) {
            this.x = x;
            this.y = y;
            this.g = g;
            this.h = h;
            this.parent = parent;
        }

        int f() { return g + h; }
    }

    public static boolean[][] bestFirst(int[][] grid, int[] start, int[] goal) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        boolean[][] path = new boolean[n][n];

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::f));
        pq.add(new Node(start[0], start[1], 0,
                DistanceCalculator.manhattanDistance(start[0], start[1], goal[0], goal[1]), null));

        Node end = null;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (visited[current.x][current.y]) continue;
            visited[current.x][current.y] = true;

            if (current.x == goal[0] && current.y == goal[1]) {
                end = current;
                break;
            }

            int[][] moves = {{-1,0},{1,0},{0,-1},{0,1}};
            for (int[] m : moves) {
                int nx = current.x + m[0];
                int ny = current.y + m[1];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == 0 && !visited[nx][ny]) {
                    int g = current.g + 1;
                    int h = DistanceCalculator.manhattanDistance(nx, ny, goal[0], goal[1]);
                    pq.add(new Node(nx, ny, g, h, current));
                }
            }
        }

        // Reconstruir caminho se chegou
        if (end != null) {
            Node cur = end;
            while (cur != null) {
                path[cur.x][cur.y] = true;
                cur = cur.parent;
            }
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

        boolean[][] path = bestFirst(grid, start, goal);

        System.out.println("\nResultado Best-First Search:");
        PathVisualization.printGridWithPath(grid, path);
    }
}