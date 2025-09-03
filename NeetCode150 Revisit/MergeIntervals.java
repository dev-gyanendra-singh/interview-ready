package ReviseAgain;

import java.util.*;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;

        // Step 1: Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> merged = new ArrayList<>();
        int[] current = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            int[] next = intervals[i];

            if (next[0] <= current[1]) {
                // Overlap — merge intervals
                current[1] = Math.max(current[1], next[1]);
            } else {
                // No overlap — save current and move to next
                merged.add(current);
                current = next;
            }
        }

        // Add the last interval
        merged.add(current);

        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        MergeIntervals mi = new MergeIntervals();
        int[][] result = mi.merge(new int[][]{
                {1, 3}, {2, 6}, {8, 10}, {15, 18}
        });

        for (int[] interval : result) {
            System.out.println(Arrays.toString(interval));
        }
    }
}
