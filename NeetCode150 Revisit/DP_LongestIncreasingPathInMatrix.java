package ReviseAgain;

import java.util.Scanner;

public class DP_LongestIncreasingPathInMatrix {
        static int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        static int m, n;

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter number of rows: ");
            m = sc.nextInt();
            System.out.print("Enter number of columns: ");
            n = sc.nextInt();

            int[][] matrix = new int[m][n];
            System.out.println("Enter matrix elements:");

            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    matrix[i][j] = sc.nextInt();

            System.out.println("Longest Increasing Path Length: " + longestIncreasingPath(matrix));
        }

        public static int longestIncreasingPath(int[][] matrix) {
            if (matrix == null || matrix.length == 0) return 0;

            int[][] dp = new int[m][n];
            int maxLen = 0;

            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    maxLen = Math.max(maxLen, dfs(matrix, i, j, dp));

            return maxLen;
        }

        public static int dfs(int[][] matrix, int i, int j, int[][] dp) {
            if (dp[i][j] != 0) return dp[i][j];

            int max = 1; // at least the cell itself
            for (int[] d : dirs) {
                int x = i + d[0], y = j + d[1];
                if (x >= 0 && y >= 0 && x < m && y < n && matrix[x][y] > matrix[i][j]) {
                    max = Math.max(max, 1 + dfs(matrix, x, y, dp));
                }
            }
            dp[i][j] = max;
            return max;
        }
    }

