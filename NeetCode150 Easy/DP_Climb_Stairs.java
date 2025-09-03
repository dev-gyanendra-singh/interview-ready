package NeetCode150;

public class DP_Climb_Stairs {

    public static void main(String[] args) {
        DP_Climb_Stairs cs = new DP_Climb_Stairs();
        System.out.println(cs.climbStairs(5));  // Output: 8
    }

    private int climbStairs(int n) {
        if (n <= 2) return n;
        int[] dp = new int[n + 1];
        dp[1] = 1; // one = 1
        dp[2] = 2; // two = 2
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2]; //temp, one = two, two = temp
        }

        return dp[n];
    }

}
