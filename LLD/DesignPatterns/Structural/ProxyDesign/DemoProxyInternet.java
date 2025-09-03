package LLD.DesignPatterns.Structural.ProxyDesign;

public class DemoProxyInternet {
    public static void main(String[] args) {
        Internet internet = new ProxyInternet();

        try {
            internet.connectTo("google.com");   // Allowed
            internet.connectTo("facebook.com"); // Blocked
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
