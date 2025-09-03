package ReviseAgain;

import java.util.Arrays;
import java.util.Stack;

public class Stack_LargestAreaOfHistogram {
    public static void main(String[] args) {
        Stack_LargestAreaOfHistogram main = new Stack_LargestAreaOfHistogram();
        System.out.println(main.largestRectangleArea(new int[]{7, 1, 7, 2, 2, 4}));
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {

            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            if (!stack.isEmpty())
                right[i] = stack.peek();
            stack.push(i);
        }

        int area = 0;
        for (int i = 0; i < n; i++) {
            int width = -left[i] + right[i] - 1;
            area = Math.max(area, width * heights[i]);
        }
        return area;
    }
}
