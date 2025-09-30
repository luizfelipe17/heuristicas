import java.util.*;

public class BuscaGulosa {

    static class Node {
        int x, y, distanciaPercorrida, heuristica;
        Node parent;

        Node(int x, int y, int distanciaPercorrida, int heuristica, Node parent) {
            this.x = x;
            this.y = y;
            this.distanciaPercorrida = distanciaPercorrida;
            this.heuristica = heuristica;
            this.parent = parent;
        }

        int f() { return distanciaPercorrida + heuristica; }
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
                    int distanciaPercorrida = current.distanciaPercorrida + 1;
                    int heuristica = DistanceCalculator.manhattanDistance(nx, ny, goal[0], goal[1]);
                    pq.add(new Node(nx, ny, distanciaPercorrida, heuristica, current));
                }
            }
        }

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

        System.out.println("\nResultado 'Busca Gulosa':");
        PathVisualization.printGridWithPath(grid, path);
    }
}