package NeetCode150;

public class DP_House_Robber {
    public int rob(int[] nums) {
        int n = nums.length;

        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        // ak chorke hi churana hai
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);  // isme bhi space optimize kiya jaa skta h...2 var leke
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        DP_House_Robber hr = new DP_House_Robber();
        int[] nums = {2, 7, 9, 3, 1};
        System.out.println(hr.rob(nums)); // Output: 12
    }

}
