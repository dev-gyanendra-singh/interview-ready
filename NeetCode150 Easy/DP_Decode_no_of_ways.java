package NeetCode150;

// dp[i] = dp[i - 1] + dp[i - 2]
public class DP_Decode_no_of_ways {
    public static void main(String[] args) {
        DP_Decode_no_of_ways dw = new DP_Decode_no_of_ways();
        String s = "226";
        System.out.println(dw.numDecodings(s)); // Output: 3
    }

    private int numDecodings(String s) {
        if (s == null || s.isEmpty() || s.charAt(0) == '0') return 0;
        int n = s.length();
        int[] dp = new int[n + 1];

        dp[0] = 1;  // Empty string has 1 way to decode
        dp[1] = 1;  // First char is valid (not '0'), so 1 way
        for (int i = 2; i <= n; i++) {
            char oneDigit = s.charAt(i - 1);
            char twoDigit1 = s.charAt(i - 2);
            int twoDigitVal = Integer.parseInt(s.substring(i - 2, i));

            // Check 1-digit decode
            if (oneDigit != '0') {
                dp[i] += dp[i - 1];
            }

            // Check 2-digit decode (10 to 26)
            if (twoDigit1 != '0' && twoDigitVal >= 10 && twoDigitVal <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }

}
