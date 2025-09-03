package LLD.SplitWise;

abstract class Split {
    private final User user;
    private double amount;

    public Split(User user) {
        this.user = user;
    }

    public User getUser() { return user; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}





