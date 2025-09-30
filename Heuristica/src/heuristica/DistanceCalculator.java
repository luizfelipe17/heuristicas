public class DistanceCalculator {

    // Calcula a dist??ncia de Manhattan entre dois pontos
    public static int manhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static void main(String[] args) {
        int[] start = {0, 0}; // Posi?$)G!p??o atual
        int[] goal = {4, 4};  // Objetivo final

        // Exemplo: vizinho na c??lula (1, 0)
        int[] neighbor = {1, 0};

        int distance = manhattanDistance(neighbor[0], neighbor[1], goal[0], goal[1]);
        System.out.println("Dist??ncia at?? o objetivo: " + distance);
    }
}
