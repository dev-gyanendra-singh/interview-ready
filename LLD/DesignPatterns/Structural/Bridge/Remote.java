package LLD.DesignPatterns.Structural.Bridge;

public class Remote {
    protected Device device;

    Remote(Device device) {
        this.device = device;
    }

    public void powerOn() {
        device.turnOn();
    }

    public void powerOff() {
        device.turnOff();
    }
}
