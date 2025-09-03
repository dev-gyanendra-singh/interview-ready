package NeetCode150;

import java.util.*;

public class DP_Work_Break_In_Dict {

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int n = s.length();

        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // Empty string is always valid

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                String word = s.substring(j, i);
                if (dp[j] && wordSet.contains(word)) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        DP_Work_Break_In_Dict wb = new DP_Work_Break_In_Dict();
        String s = "applepenapple";
        List<String> dict = Arrays.asList("apple", "pen");
        System.out.println(wb.wordBreak(s, dict));  // Output: true
    }
}
