package ReviseAgain;

import java.util.Scanner;

public class DP_Target_Sum {

    // P - N = target
    // P+ N = sumTotal
    // int sum = (target + total) / 2; --> keval even k case me hme nikalna prega ki kitne subset hain array k jisse Sum sum k barabar bn jai

    public static int findTargetSumWays(int[] nums, int target) {
        int total = 0;
        for (int num : nums) total += num;

        // P = (target + total) / 2
        if ((target + total) % 2 != 0 || target > total || target < -total) return 0;

        int sum = (target + total) / 2;
        return countSubsets(nums, sum);
    }

    // Classic subset sum count
    private static int countSubsets(int[] nums, int sum) {
        int n = nums.length;
        int[][] dp = new int[n + 1][sum + 1];

        dp[0][0] = 1; // Only 1 way to reach 0: pick nothing

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][sum];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] nums = new int[n];
        System.out.println("Enter elements of the array:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        System.out.print("Enter target sum: ");
        int target = sc.nextInt();

        int result = findTargetSumWays(nums, target);
        System.out.println("Number of ways to reach target: " + result);

        sc.close();
    }
}
