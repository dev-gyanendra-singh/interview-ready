package ReviseAgain;

import java.util.ArrayList;
import java.util.List;

public class PartitionLevels {

/*

Example 1:

Input: s = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
Example 2:

Input: s = "eccbbbbdec"
Output: [10]

✅ Key Idea:
* First, record the last index where each character appears.

Then, greedily form partitions:

Start from the first character.

Extend the partition to include the last occurrence of every character seen so far.

When you reach the furthest last occurrence of all characters in the current partition → that’s the end of a partition!

*/
    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];

        // Step 1: Record the last position of each character
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        List<Integer> result = new ArrayList<>();
        int start = 0, end = 0;

        // Step 2: Scan and partition
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);

            // When we've reached the end of a partition
            if (i == end) {
                result.add(end - start + 1);
                start = i + 1;
            }
        }

        return result;
    }
}
