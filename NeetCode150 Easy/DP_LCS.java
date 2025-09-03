package NeetCode150;

import java.util.*;

public class DP_LCS {
    public static int lengthOfLIS(int[] nums) {
        List<Integer> lis = new ArrayList<>();
        for (int num : nums) {
            int idx = Collections.binarySearch(lis, num);
            if (idx < 0) idx = -(idx + 1);
            if (idx == lis.size()) lis.add(num);
            else lis.set(idx, num);
        }
        return lis.size();
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 1, 8, 5, 6};
        System.out.println(lengthOfLIS(nums)); // Output: 3
    }

}
