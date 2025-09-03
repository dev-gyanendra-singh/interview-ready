package LLD.SplitWise;

import java.util.List;

public class Expense {
    private final String description;
    private final double amount;
    private final User paidBy;
    private final List<Split> splits;
    private final ExpenseType expenseType;

    public Expense(String description, double amount, User paidBy,
                   List<Split> splits, ExpenseType type) {
        this.description = description;
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = splits;
        this.expenseType = type;
    }

    public List<Split> getSplits() { return splits; }
    public User getPaidBy() { return paidBy; }
    public double getAmount() { return amount; }
}
