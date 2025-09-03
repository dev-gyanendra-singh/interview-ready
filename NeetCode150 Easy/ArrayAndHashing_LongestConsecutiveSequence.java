package NeetCode150;

import java.util.HashSet;
import java.util.Set;

public class ArrayAndHashing_LongestConsecutiveSequence {
    public static void main(String[] args) {
        int[] longestConsecutive = {2,20,4,10,3,4,5};
        System.out.println("The length of the longest consecutive integers in the array is :" + new ArrayAndHashing_LongestConsecutiveSequence().longestConsecutive(longestConsecutive));
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> longestConsecutiveSet = new HashSet<>();
        for (int num : nums) {
            longestConsecutiveSet.add(num);
        }

        int longestConsecutive = 0;
        for (int num : longestConsecutiveSet) {
            if (!longestConsecutiveSet.contains(num - 1)) {
                int currentNum = num;
                int currentLongestConsecutive = 0;
                while (longestConsecutiveSet.contains(currentNum)) {
                    currentNum++;
                    currentLongestConsecutive++;
                }
                longestConsecutive = Math.max(currentLongestConsecutive, longestConsecutive);
            }
        }
        return longestConsecutive;
    }
}
