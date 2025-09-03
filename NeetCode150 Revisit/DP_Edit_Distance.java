package ReviseAgain;

import java.util.Scanner;

public class DP_Edit_Distance {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter word1: ");
        String word1 = sc.nextLine();
        System.out.print("Enter word2: ");
        String word2 = sc.nextLine();

        int result = minDistance(word1, word2);
        System.out.println("Minimum Edit Distance: " + result);
        sc.close();
    }

    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        // Base cases
        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;

        // Fill dp table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j - 1],  // Replace
                            Math.min(
                                    dp[i - 1][j],  // Delete
                                    dp[i][j - 1]   // Insert
                            )
                    );
                }
            }
        }

        return dp[m][n];
    }
}

