package ReviseAgain;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Graph_Multi_Source_BSF_Nearest_To_Zero {
    private static final int INF = Integer.MAX_VALUE;
    private static final int[][] DIRECTIONS = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };


    public static void main(String[] args) {
        Graph_Multi_Source_BSF_Nearest_To_Zero s = new Graph_Multi_Source_BSF_Nearest_To_Zero();
        int INF = Integer.MAX_VALUE;

        int[][] rooms = {
                {INF, -1, 0, INF},
                {INF, INF, INF, -1},
                {INF, -1, INF, -1},
                {0, -1, INF, INF}
        };

        s.wallsAndGates(rooms);

        for (int[] row : rooms) {
            System.out.println(Arrays.toString(row));
        }

        // Expected output:
        // [3, -1,  0,  1]
        // [2,  2,  1, -1]
        // [1, -1,  2, -1]
        // [0, -1,  3,  4]
    }

    private void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) return;

        int rows = rooms.length, cols = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0], col = curr[1];
            for (int[] direction : DIRECTIONS) {
                int newRow = row + direction[0], newCol = col + direction[1];
                // negative cases
                if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) continue;
                if (rooms[newRow][newCol] == INF) {
                    rooms[newRow][newCol] = rooms[row][col] + 1;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

    }
}


