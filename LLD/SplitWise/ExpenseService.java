package LLD.SplitWise;

import java.util.List;

public class ExpenseService {
    public Expense createExpense(String description, double amount,
                                 User paidBy, List<Split> splits, ExpenseType type) {
        if (type == ExpenseType.EQUAL) {
            int totalUsers = splits.size();
            double splitAmount = Math.round((amount / totalUsers) * 100.0) / 100.0;
            for (Split split : splits) {
                if(split.getUser().equals(paidBy)) {
                    split.setAmount(amount-splitAmount);
                } else {
                    split.setAmount(-splitAmount);
                }
            }
        }

        return new Expense(description,
                amount, paidBy, splits, type);
    }
}