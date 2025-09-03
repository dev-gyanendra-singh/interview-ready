package NeetCode150;

public class DP_Robber_In_Circle {
    public int rob(int[] nums) {
        int n = nums.length;

        if (n == 0) return 0;
        if (n == 1) return nums[0];

        // Case 1: Exclude last house
        int max1 = robLinear(nums, 0, n - 2);
        // Case 2: Exclude first house
        int max2 = robLinear(nums, 1, n - 1);

        return Math.max(max1, max2);
    }

    // Reuse normal house robber logic on a subarray
    private int robLinear(int[] nums, int start, int end) {
        int prev2 = 0, prev1 = 0;

        for (int i = start; i <= end; i++) {
            int curr = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }

    public static void main(String[] args) {
        DP_Robber_In_Circle hr2 = new DP_Robber_In_Circle();
        int[] nums = {2, 3, 2};
        System.out.println(hr2.rob(nums)); // Output: 3
    }
}
