package ReviseAgain;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingWindow_Max_SlidingWindowWithGIvenWindowSize {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SlidingWindow_Max_SlidingWindowWithGIvenWindowSize().maxSlidingWindow(new int[]{1, 2, 1, 0, 4, 2, 6}, 3)));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            // shuruat me jyada elements hai to unhe hata do
            if (!stack.isEmpty() && stack.peekFirst() < i - k + 1) {
                stack.pollFirst();
            }
            // last me se jo bhi elements current element se bade hai unhe bhi hta do.... is tarah se first element pr maximum value wala index bna rhega current window ka
            while (!stack.isEmpty() && nums[stack.peekLast()] < nums[i])
                stack.pollLast();

            stack.offerLast(i);

            if (i >= k - 1) {
                result[i - k + 1] = nums[stack.peekFirst()];
            }
        }

        return result;

    }
}

