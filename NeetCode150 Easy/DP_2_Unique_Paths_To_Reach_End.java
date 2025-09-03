package NeetCode150;

public class DP_2_Unique_Paths_To_Reach_End {

    public static void main(String[] args) {
        DP_2_Unique_Paths_To_Reach_End solver = new DP_2_Unique_Paths_To_Reach_End();

        int m = 3;
        int n = 7;

        int result = solver.uniquePaths(m, n);
        System.out.println("Number of unique paths in " + m + "x" + n + " grid: " + result);
    }

    private int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int i = 1; i < n; i++) dp[0][i] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
