package NeetCode150;

import java.util.PriorityQueue;

public class Greedy_kTh_Largest_Element {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        return minHeap.peek();
    }

    public static void main(String[] args) {
        Greedy_kTh_Largest_Element solver = new Greedy_kTh_Largest_Element();
        System.out.println(solver.findKthLargest(new int[]{3,2,1,5,6,4}, 2)); // Output: 5
    }
}
