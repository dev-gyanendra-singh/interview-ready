package LLD.DesignPatterns.Structural.Bridge;

public class MainApp {
    public static void main(String[] args) {
        Device tv = new TV();
        Remote remote1 = new Remote(tv);
        remote1.powerOn();
        remote1.powerOff();

        Device radio = new Radio();
        AdvancedRemote remote2 = new AdvancedRemote(radio);
        remote2.powerOn();
        remote2.setVolume(30);
        remote2.powerOff();
    }
}

