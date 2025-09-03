package NeetCode150;

import java.util.HashSet;
import java.util.Set;

public class SlidingWindow_LongestSubstringWithoutRepeatingCharacter {
        public static void main(String[] args) {
            System.out.println(new SlidingWindow_LongestSubstringWithoutRepeatingCharacter().lengthOfLongestSubstring("zxyzxyz"));
            System.out.println(new SlidingWindow_LongestSubstringWithoutRepeatingCharacter().lengthOfLongestSubstring("xxxx"));
        }

        public int lengthOfLongestSubstring(String s) {
            Set<Character> set = new HashSet<>();
            int max = 0, l=0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                while (set.contains(c)) {
                    set.remove(s.charAt(l++));
                }

                set.add(c);
                max = Math.max(set.size(), max);
            }
            return max;

        }
}
