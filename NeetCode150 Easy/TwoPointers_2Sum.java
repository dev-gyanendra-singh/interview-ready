package NeetCode150;

import java.util.Arrays;
import java.util.HashMap;

public class TwoPointers_2Sum {
    public static void main(String[] args) {
        int[] arrIndexes = new TwoPointers_2Sum().twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(Arrays.toString(arrIndexes));
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }
}
