package NeetCode150;

import java.util.HashMap;
import java.util.Map;

public class SlidingWindow_MinimunWindowSuchThat_AllCharactersOf_T_Are_FoundIn_S {
    public static void main(String[] args) {
        System.out.println(new SlidingWindow_MinimunWindowSuchThat_AllCharactersOf_T_Are_FoundIn_S().minWindow("OUZODYXAZV", "XYZ"));
        System.out.println(new SlidingWindow_MinimunWindowSuchThat_AllCharactersOf_T_Are_FoundIn_S().minWindow("xyz", "xyz"));
    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> targetMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }
        HashMap<Character, Integer> windowMap = new HashMap<>();
        int left = 0, min = Integer.MAX_VALUE, count = 0, start = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);

            if (targetMap.containsKey(c) && windowMap.get(c) <= targetMap.get(c)) {
                count++;
            }

            while (count == t.length()) {
                if (right - left + 1 < min) {
                    min = right - left + 1;
                    start = left;
                }
                char tc = s.charAt(left);
                windowMap.put(tc, windowMap.getOrDefault(tc, 0) - 1);
                if (targetMap.containsKey(tc) && windowMap.get(tc) < targetMap.get(tc)) {
                    count--;
                }
                left++;
            }
        }
        return s.substring(start, start + min);
    }
}
