package NeetCode150;

import java.util.HashMap;
import java.util.Map;

public class SlidingWindow_LongestSubstringWithoutRepeatingChars_K_Swap_Allowed {
    public static void main(String[] args) {
        System.out.println(new SlidingWindow_LongestSubstringWithoutRepeatingChars_K_Swap_Allowed().characterReplacement("XYYX", 2));
        System.out.println(new SlidingWindow_LongestSubstringWithoutRepeatingChars_K_Swap_Allowed().characterReplacement("AAABABB", 1));
    }

    public int characterReplacement(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int l = 0, res = 0, maxCount = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
            maxCount = Math.max(maxCount, map.getOrDefault(c, 0));
            while (i - l + 1 - maxCount > k) {
                map.put(s.charAt(l), map.getOrDefault(s.charAt(l), 0) - 1);
                l++;
            }
            res = Math.max(res, i - l + 1);
        }
        return res;
    }
}

