package ReviseAgain;

import java.util.Scanner;

public class DP_2_Buy_Sell_Stock_With_Cool_Down {

    // Core DP method
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;

        int[] hold = new int[n];
        int[] sold = new int[n];
        int[] rest = new int[n];

        hold[0] = -prices[0];  // Bought on day 0 ... profit when hold(normal hold and buy hold also)
        sold[0] = 0;           // Can't sell on day 0 ....
        rest[0] = 0;           // No action taken

        for (int i = 1; i < n; i++) {
            hold[i] = Math.max(hold[i - 1], rest[i - 1] - prices[i]);
            sold[i] = hold[i - 1] + prices[i];
            rest[i] = Math.max(rest[i - 1], sold[i - 1]);
        }

        return Math.max(sold[n - 1], rest[n - 1]); // can't end with hold
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DP_2_Buy_Sell_Stock_With_Cool_Down obj = new DP_2_Buy_Sell_Stock_With_Cool_Down();

        // Take input from user
        System.out.print("Enter number of days: ");
        int n = sc.nextInt();

        int[] prices = new int[n];
        System.out.println("Enter stock prices:");
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
        }

        int result = obj.maxProfit(prices);
        System.out.println("Maximum Profit with Cooldown: " + result);

        sc.close();
    }
}

