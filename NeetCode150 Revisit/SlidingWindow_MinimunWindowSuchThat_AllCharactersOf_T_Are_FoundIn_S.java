package ReviseAgain;

import java.util.HashMap;
import java.util.Map;

public class SlidingWindow_MinimunWindowSuchThat_AllCharactersOf_T_Are_FoundIn_S {
    public static void main(String[] args) {
        System.out.println(new SlidingWindow_MinimunWindowSuchThat_AllCharactersOf_T_Are_FoundIn_S().minWindow("OUZODYXAZV", "XYZ"));
        System.out.println(new SlidingWindow_MinimunWindowSuchThat_AllCharactersOf_T_Are_FoundIn_S().minWindow("xyz", "xyz"));
    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> targetMap = new HashMap<>();
        // saare required window ke characters idhar rkh lo
        for (char c : t.toCharArray()) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }
        HashMap<Character, Integer> windowMap = new HashMap<>();
        int left = 0, min = Integer.MAX_VALUE, count = 0, start = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);
            // pehli baat ye right side wala character target me ho and window me bhi ho... tb hme ak required character mil gya
            if (targetMap.containsKey(c) && windowMap.get(c) <= targetMap.get(c)) {
                count++;
            }

            while (count == t.length()) { // hmesha equal hone pr hi remove krne ka sochna chahiye
                if (right - left + 1 < min) {
                    min = right - left + 1;
                    start = left;
                }
                char tc = s.charAt(left);
                windowMap.put(tc, windowMap.getOrDefault(tc, 0) - 1);
                // sbse leftmost ko dekho vo target window me hai and uski frequency to obv windowMap me km ho gai hai isliye required char bhi km ho jainge
                if (targetMap.containsKey(tc) && windowMap.get(tc) < targetMap.get(tc)) {
                    count--;
                }
                left++;
            }
        }
        return s.substring(start, start + min);
    }
}
