package LLD.SplitWise;
import java.util.*;


public class SplitwiseApp {
    public static void main(String[] args) {
        // Create Users
        User u1 = new User("u1", "Alice", "alice@example.com");
        User u2 = new User("u2", "Bob", "bob@example.com");
        User u3 = new User("u3", "Carol", "carol@example.com");

        // Expense Service and Balance Sheet
        ExpenseService expenseService = new ExpenseService();
        BalanceSheet balanceSheet = new BalanceSheet();

        // Create Expense: Alice pays $300 for dinner split equally among 3
        List<Split> splits = Arrays.asList(new EqualSplit(u1), new EqualSplit(u2), new EqualSplit(u3));
        Expense expense = expenseService.createExpense("Dinner", 300.0, u1, splits, ExpenseType.EQUAL);

        // Add expense to balance sheet
        balanceSheet.addExpense(expense);

        // Show balances
        System.out.println("Current Balances:");
        balanceSheet.showBalances();
    }
}
