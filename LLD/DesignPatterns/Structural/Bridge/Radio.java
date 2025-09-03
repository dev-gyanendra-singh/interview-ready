package LLD.DesignPatterns.Structural.Bridge;

public class Radio implements Device {
    @Override
    public void turnOn() {
        System.out.println("Radio is turned ON");
    }

    @Override
    public void turnOff() {
        System.out.println("Radio is turned OFF");
    }

    @Override
    public void setVolume(int percent) {
        System.out.println("Radio volume set to " + percent + "%");
    }
}

