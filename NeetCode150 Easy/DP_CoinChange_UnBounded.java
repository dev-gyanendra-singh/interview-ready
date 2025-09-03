package NeetCode150;
import java.util.Arrays;


// dp[i] = Math.min(dp[i], dp[i - coin] + 1)
public class DP_CoinChange_UnBounded { // infite coin of each denomination are available = unbounded knapsack


    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);  // Sentinel for "unreachable"
        dp[0] = 0;  // 0 coins needed to make amount 0

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        DP_CoinChange_UnBounded cc = new DP_CoinChange_UnBounded();
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(cc.coinChange(coins, amount)); // Output: 3 (5+5+1)
    }

}
