package ReviseAgain;

import java.util.Scanner;

public class DP_Distinct_SubSequences {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter string s: ");
            String s = sc.nextLine();
            System.out.print("Enter string t: ");
            String t = sc.nextLine();

            int result = numDistinct(s, t);
            System.out.println("Number of distinct subsequences: " + result);
        }

        public static int numDistinct(String s, String t) {
            int m = s.length(), n = t.length();
            int[][] dp = new int[m + 1][n + 1];
            // dp[i][j] --> number of ways of making t's till jth index using... i chars from s

            // Base case: empty t
            for (int i = 0; i <= m; i++) dp[i][0] = 1;

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }

            return dp[m][n];
        }
    }

