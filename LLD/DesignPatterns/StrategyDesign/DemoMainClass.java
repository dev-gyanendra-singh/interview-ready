package LLD.DesignPatterns.StrategyDesign;

public class DemoMainClass {
    public static void main(String[] args) {
        PaymentManager context = new PaymentManager();

       // context.setPaymentStrategy(new CreditCardPayment("1234-5678-9876-5432"));
        context.payAmount(new CreditCardPayment("1234-5678-9876-5432"),1000);

       // context.setPaymentStrategy(new PayPalPayment("user@example.com"));
        context.payAmount(new PayPalPayment("user@example.com"), 500);

       // context.setPaymentStrategy(new UpiPayment("user@upi"));
        context.payAmount(new UpiPayment("user@upi"), 750);
    }
}

