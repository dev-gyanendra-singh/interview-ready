package LLD.DesignPatterns.Structural.Bridge;

public class TV implements Device {
    @Override
    public void turnOn() {
        System.out.println("TV is turned ON");
    }

    @Override
    public void turnOff() {
        System.out.println("TV is turned OFF");
    }

    @Override
    public void setVolume(int percent) {
        System.out.println("TV volume set to " + percent + "%");
    }
}

