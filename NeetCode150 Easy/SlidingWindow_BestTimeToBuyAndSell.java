package NeetCode150;

public class SlidingWindow_BestTimeToBuyAndSell {
    public static void main(String[] args) {
        System.out.println(new SlidingWindow_BestTimeToBuyAndSell().maxProfit(new int[]{10, 8, 7, 5, 2}));
    }

    public int maxProfit(int[] prices) {
        int res = 0, buy = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (buy < prices[i]) {
                res = Math.max(res, prices[i] - buy);
            } else {
                buy = prices[i];
            }
        }
        return res;
    }

}
