package LLD.SplitWise;

import java.util.HashMap;
import java.util.Map;

public class BalanceSheet {
    private final Map<String, Map<String, Double>> balances = new HashMap<>();
    public void addExpense(Expense expense) {
        String paidById = expense.getPaidBy().getId();

        for (Split split : expense.getSplits()) {
            String paidToId = split.getUser().getId();
            if (paidToId.equals(paidById)) continue;
            double amount = split.getAmount();
            balances.putIfAbsent(paidById, new HashMap<>());
            balances.get(paidById).put(paidToId, balances.get(paidById).getOrDefault(paidToId, 0.0) - amount);
            balances.putIfAbsent(paidToId, new HashMap<>());
            balances.get(paidToId).put(paidById, balances.get(paidToId).getOrDefault(paidById, 0.0) + amount);
            //   A, B -100
            //   A, C -100
            //   B, A 100
            //   C, A  100
        }
    }
    public void showBalances() {
        boolean empty = true;
        for (String user1 : balances.keySet()) {
            for (String user2 : balances.get(user1).keySet()) {
                double amount = balances.get(user1).get(user2);
                if (amount > 0) {
                    System.out.printf(" %s will get from %s back %s Rs" + user2, user1, amount);
                    // B, A 100
                    // A will get from B 100 Rs back.
                    empty = false;
                }
                else if (amount < 0) {
                    // A, B -100
                    // B will give Rs 100 to A.
                    System.out.printf("%s will give Rs %s back to %s", user2, -amount, user1);
                }
            }
        }
        if (empty) {
            System.out.println("No balances");
        }
    }
}