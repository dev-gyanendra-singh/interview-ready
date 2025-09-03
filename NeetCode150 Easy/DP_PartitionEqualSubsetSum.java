package NeetCode150;

public class DP_PartitionEqualSubsetSum {
        public boolean canPartition(int[] nums) {
            int totalSum = 0;
            for (int num : nums) totalSum += num;

            if (totalSum % 2 != 0) return false; // odd sum → no equal partition

            int target = totalSum / 2;
            boolean[] dp = new boolean[target + 1];
            dp[0] = true;

            for (int num : nums) {
                for (int i = target; i >= num; i--) {
                    dp[i] = dp[i] || dp[i - num];
                }
            }

            return dp[target];
        }

        public static void main(String[] args) {
            DP_PartitionEqualSubsetSum partition = new DP_PartitionEqualSubsetSum();
            int[] nums = {1, 5, 11, 5};
            System.out.println(partition.canPartition(nums)); // Output: true
        }


}
