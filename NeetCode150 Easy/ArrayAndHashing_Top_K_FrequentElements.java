package NeetCode150;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ArrayAndHashing_Top_K_FrequentElements {
    public static void main(String[] args) {
        int[] arrIndexes = {1,2,3,4,5,6,7,8,9,10, 10, 10, 1, 1, 1};
        System.out.println(Arrays.toString(new ArrayAndHashing_Top_K_FrequentElements().topKFrequent(arrIndexes, 2)));

    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(freqMap.entrySet());
        int[] res = new int[k];
        for (int i = 0; i<k; i++) {
            res[i] = pq.poll().getKey();
        }
        return res;

    }
}
