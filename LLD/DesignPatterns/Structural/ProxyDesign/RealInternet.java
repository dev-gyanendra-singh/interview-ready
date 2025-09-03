package LLD.DesignPatterns.Structural.ProxyDesign;


public class RealInternet implements Internet {
    @Override
    public void connectTo(String serverHost) {
        System.out.println("Connecting to " + serverHost);
    }
}

