package LLD.DesignPatterns.StrategyDesign;

public class PaymentManager {

    public void payAmount(PaymentStrategy paymentStrategy, int amount) {

        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy not set.");
        }
        paymentStrategy.pay(amount);
    }
}
