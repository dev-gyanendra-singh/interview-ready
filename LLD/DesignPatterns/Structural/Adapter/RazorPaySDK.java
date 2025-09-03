package LLD.DesignPatterns.Structural.Adapter;

public class RazorPaySDK {
    public void makePayment(String userToken, int amountInPaise) {
        System.out.println("Payment of ₹" + amountInPaise/100 + " done via RazorPay for " + userToken);
    }
}

