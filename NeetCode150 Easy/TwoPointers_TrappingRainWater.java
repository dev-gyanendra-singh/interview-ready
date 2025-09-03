package NeetCode150;

import WorkingModule.Main;

public class TwoPointers_TrappingRainWater {
    public static void main(String[] args) {
        System.out.println(new TwoPointers_TrappingRainWater().trap(new int[]{1,7,2,5,4,7,3,6}));
    }

    public int trap(int[] height) {
        int l = 0, r = height.length - 1;
        int res = 0, left_max = 0, right_max = 0;
        while (l < r) {
            if (height[l] < height[r]) {
                left_max = Math.max(left_max, height[l]);
                if (left_max < height[l]) {
                    res += left_max - height[l];
                }
                l++;

            } else {
                right_max = Math.max(right_max, height[r]);
                if (right_max > height[r]) {
                    res += right_max - height[r];
                }
                r--;

            }
        }
        return res;
    }
}
