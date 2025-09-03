package ReviseAgain;

import java.util.Scanner;

public class DP_Interleving_String {

    public static boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();

        if (s3.length() != m + n) return false;

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        // Fill first row
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        // Fill first column
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }

        // Fill the rest of the table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) ||
                        (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter string s1:");
        String s1 = sc.nextLine();

        System.out.println("Enter string s2:");
        String s2 = sc.nextLine();

        System.out.println("Enter string s3:");
        String s3 = sc.nextLine();

        boolean result = isInterleave(s1, s2, s3);
        System.out.println("Is interleaving: " + result);
    }
}

