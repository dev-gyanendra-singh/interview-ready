package NeetCode150;

public class SlidingWindow_PermutationOfOneStringInAnother {

    public static void main(String[] args) {
        System.out.println(new SlidingWindow_PermutationOfOneStringInAnother().checkInclusion("abc", "lecabee"));
        System.out.println(new SlidingWindow_PermutationOfOneStringInAnother().checkInclusion("abc", "lecaabee"));
    }

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] s1Count = new int[26];
        int[] s2Count = new int[26];

        // Count frequencies of characters in s1
        for (int i = 0; i < s1.length(); i++) {
            s1Count[s1.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s2.length(); i++) {
            s2Count[s2.charAt(i) - 'a']++;

            // Once the window is larger than s1, remove the leftmost character
            if (i >= s1.length()) {
                s2Count[s2.charAt(i - s1.length()) - 'a']--;
            }

            // Compare the counts
            if (i >= s1.length() - 1 && matches(s1Count, s2Count)) {
                return true;
            }
        }

        return false;
    }

    // Helper to compare frequency arrays
    private boolean matches(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }
}
