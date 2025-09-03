package NeetCode150;

import java.util.Arrays;
import java.util.Stack;

public class Stack_NextWarmerDay {
    public static void main(String[] args) {
        Stack_NextWarmerDay main = new Stack_NextWarmerDay();
        System.out.println(Arrays.toString(main.dailyTemperatures(new int[]{30, 38, 30, 36, 35, 40, 28})));
    }

    public int[] dailyTemperatures(int[] temperatures) {

        Stack<Integer> stack = new Stack<>();
        int n = temperatures.length;
        int[] result = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return result;

    }
}
