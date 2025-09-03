package LLD.DesignPatterns.Structural.ProxyDesign;

import java.util.Arrays;
import java.util.List;

public class ProxyInternet implements Internet {
    private Internet internet = new RealInternet();
    private static final List<String> bannedSites = Arrays.asList("facebook.com", "youtube.com");

    @Override
    public void connectTo(String serverHost) throws Exception {
        if (bannedSites.contains(serverHost.toLowerCase())) {
            throw new Exception("Access Denied to: " + serverHost);
        }

        internet.connectTo(serverHost);
    }
}

