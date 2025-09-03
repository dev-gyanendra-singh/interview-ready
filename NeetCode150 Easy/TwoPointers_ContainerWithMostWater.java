package NeetCode150;

public class TwoPointers_ContainerWithMostWater {
    public static void main(String[] args) {
        System.out.println(new TwoPointers_ContainerWithMostWater().maxArea(new int[]{1, 7, 2, 5, 4, 7, 3, 6}));
    }

    public int maxArea(int[] heights) {
        int globalMaxArea = 0;
        int l = 0, r = heights.length - 1;
        while (l < r) {
            int area = Math.min(heights[l], heights[r]) * (r - l);
            globalMaxArea = Math.max(area, globalMaxArea);
            if (heights[l] < heights[r]) {
                l++;
            } else {
                r--;
            }
        }
        return globalMaxArea;
    }
}
