package ReviseAgain;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Graph_Mark_All_Surrounded_Field {

    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};


    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;

        int m = board.length, n = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') queue.offer(new int[]{i, 0});
            if (board[i][n - 1] == 'O') queue.offer(new int[]{i, n - 1});
        }
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') queue.offer(new int[]{0, j});
            if (board[m - 1][j] == 'O') queue.offer(new int[]{m - 1, j});
        }
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int row = cur[0], col = cur[1];
            if (row < 0 || row >= m || col < 0 || col >= n || board[row][col] != 'O') continue;
            board[row][col] = 'T';
            for (int[] dir : DIRECTIONS) {
                queue.offer(new int[]{row + dir[0], col + dir[1]});
            }

        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X'; // Captured
                } else if (board[i][j] == 'T') {
                    board[i][j] = 'O'; // Revert safe ones
                }
            }
        }

    }


    public static void main(String[] args) {
        Graph_Mark_All_Surrounded_Field sol = new Graph_Mark_All_Surrounded_Field();

        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };

        sol.solve(board);

        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }

        // Expected:
        // [X, X, X, X]
        // [X, X, X, X]
        // [X, X, X, X]
        // [X, O, X, X]
    }
}
