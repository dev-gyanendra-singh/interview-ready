package NeetCode150;


/*There are 2n - 1 possible centers:

n odd-length centers (character centers)

n - 1 even-length centers (between characters)*/
public class DP_Count_Palindrome {
    public int countSubstrings(String s) {
        int count = 0;

        for (int center = 0; center < 2 * s.length() - 1; center++) {
            int left = center / 2;
            int right = left + center % 2;

            while (left >= 0 && right < s.length() &&
                    s.charAt(left) == s.charAt(right)) {
                count++;
                left--;
                right++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        DP_Count_Palindrome ps = new DP_Count_Palindrome();
        String s = "aaa";
        System.out.println(ps.countSubstrings(s)); // Output: 6 ("a","a","a","aa","aa","aaa")
    }
}
