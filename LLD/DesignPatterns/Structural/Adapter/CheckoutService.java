package LLD.DesignPatterns.Structural.Adapter;

public class CheckoutService {
    private PaymentProcessor processor;

    public CheckoutService(PaymentProcessor processor) {
        this.processor = processor;
    }

    public void checkout(String customerId, double amount) {
        processor.pay(customerId, amount);
    }

    public static void main(String[] args) {
        PaymentProcessor adapter = new RazorPayAdapter();
        CheckoutService checkout = new CheckoutService(adapter);
        checkout.checkout("cust123", 999.99);
    }
}

