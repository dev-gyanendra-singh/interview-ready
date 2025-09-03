package NeetCode150;

public class DP_MaxProductSubarray {
    public int maxProduct(int[] nums) {
        int max = nums[0];
        int currMax = nums[0];
        int currMin = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int temp = currMax;

            currMax = Math.max(nums[i], Math.max(currMax * nums[i], currMin * nums[i]));
            currMin = Math.min(nums[i], Math.min(temp * nums[i], currMin * nums[i]));

            max = Math.max(max, currMax);
        }

        return max;
    }

    public static void main(String[] args) {
        DP_MaxProductSubarray mp = new DP_MaxProductSubarray();
        int[] nums = {2, 3, -2, 4};
        System.out.println(mp.maxProduct(nums)); // Output: 6
    }
}
