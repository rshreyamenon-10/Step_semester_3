import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

public class FireDrillExits {

    public static int[][] exitDistance(char[][] plan) {
        if (plan == null || plan.length == 0 || plan[0].length == 0) {
            return new int[0][0];
        }

        int rows = plan.length;
        int cols = plan[0].length;
        int[][] dist = new int[rows][cols];
        Queue<int[]> queue = new LinkedList<>();

        // Initialize distances and queue with all 'E' (exits)
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (plan[r][c] == 'E') {
                    dist[r][c] = 0;
                    queue.offer(new int[] { r, c });
                } else if (plan[r][c] == '#') {
                    dist[r][c] = -1;
                } else {
                    dist[r][c] = -1; // Unvisited rooms
                }
            }
        }

        // Directions for moving up, down, left, right
        int[] dRow = { -1, 1, 0, 0 };
        int[] dCol = { 0, 0, -1, 1 };

        // Multi-source BFS
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0];
            int c = cell[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dRow[i];
                int nc = c + dCol[i];

                // Check bounds and if the neighbor is an unvisited open room '.'
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                    if (plan[nr][nc] == '.' && dist[nr][nc] == -1) {
                        dist[nr][nc] = dist[r][c] + 1;
                        queue.offer(new int[] { nr, nc });
                    }
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        char[][] plan = {
            {'.', '#', '.', 'E'},
            {'.', '.', '.', '#'},
            {'E', '#', '.', '.'}
        };

        int[][] result = exitDistance(plan);

        for (int[] row : result) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
