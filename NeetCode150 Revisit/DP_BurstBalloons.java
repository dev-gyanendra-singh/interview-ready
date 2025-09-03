package ReviseAgain;

import java.util.*;

public class DP_BurstBalloons {
    public static int maxCoins(int[] nums) {
        int n = nums.length;

        // Add 1 at both ends
        int[] newNums = new int[n + 2];
        newNums[0] = 1;
        newNums[n + 1] = 1;
        for (int i = 0; i < n; i++) newNums[i + 1] = nums[i];

        int[][] dp = new int[n + 2][n + 2];

        // Bottom-up interval DP
        for (int len = 2; len < n + 2; len++) {
            for (int left = 0; left < n + 2 - len; left++) {
                int right = left + len;
                for (int k = left + 1; k < right; k++) {
                    dp[left][right] = Math.max(
                            dp[left][right],
                            newNums[left] * newNums[k] * newNums[right] + dp[left][k] + dp[k][right]
                    );
                }
            }
        }

        return dp[0][n + 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of balloons:");
        int n = sc.nextInt();

        int[] nums = new int[n];
        System.out.println("Enter balloon values:");
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();

        int result = maxCoins(nums);
        System.out.println("Max coins: " + result);
    }
}

