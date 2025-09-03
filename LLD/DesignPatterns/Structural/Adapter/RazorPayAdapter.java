package LLD.DesignPatterns.Structural.Adapter;

public class RazorPayAdapter implements PaymentProcessor {
    private RazorPaySDK razorPay;

    public RazorPayAdapter() {
        this.razorPay = new RazorPaySDK();
    }

    @Override
    public void pay(String customerId, double amount) {

        int paise = (int)(amount * 100); // Convert rupees to paise
        System.out.println("Pais: " + paise);
        razorPay.makePayment(customerId, paise);
    }
}

