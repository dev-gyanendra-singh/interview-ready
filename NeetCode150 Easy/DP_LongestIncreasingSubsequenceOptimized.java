package NeetCode150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DP_LongestIncreasingSubsequenceOptimized {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int maxLen = 1;

        Arrays.fill(dp, 1); // Every number is at least LIS of length 1

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        DP_LongestIncreasingSubsequenceOptimized lis = new DP_LongestIncreasingSubsequenceOptimized();
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lis.lengthOfLIS(nums));  // Output: 4
    }

    public int lengthOfLISBinary(int[] nums) {
        List<Integer> tails = new ArrayList<>();

        for (int num : nums) {
            int idx = Collections.binarySearch(tails, num);
            if (idx < 0) {
                idx = -(idx + 1); // insertion point
            }

            if (idx == tails.size()) {
                tails.add(num);  // extend the list
            } else {
                tails.set(idx, num); // replace with smaller value
            }
        }

        return tails.size();
    }


}
