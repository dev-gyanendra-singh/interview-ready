package NeetCode150;

public class DP_Min_Cost_To_Reach_Top {

        public int minCostClimbingStairs(int[] cost) {
            int n = cost.length;

            int[] dp = new int[n + 1]; // dp[i] = min cost to reach step i

            // Base cases
            dp[0] = 0;  // No cost to start at step 0
            dp[1] = 0;  // No cost to start at step 1

            for (int i = 2; i <= n; i++) {
                dp[i] = Math.min(dp[i - 1] + cost[i - 1],
                        dp[i - 2] + cost[i - 2]);
            }

            return dp[n];  // min cost to reach the top
        }

        public static void main(String[] args) {
            DP_Min_Cost_To_Reach_Top mc = new DP_Min_Cost_To_Reach_Top();
            int[] cost = {10, 15, 20};
            System.out.println(mc.minCostClimbingStairs(cost)); // Output: 15
        }
    }
