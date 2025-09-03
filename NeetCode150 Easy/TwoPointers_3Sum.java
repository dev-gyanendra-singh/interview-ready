package NeetCode150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointers_3Sum {
    public static void main(String[] args) {
        System.out.println(new TwoPointers_3Sum().threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int l=i+1, r = nums.length-1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++;r--;
                } else if (sum < 0) {l++;} else {r--;}
            }
        }
        return result;
    }
}
