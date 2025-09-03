package LLD.DesignPatterns.Structural.Bridge;

public class AdvancedRemote extends Remote {
    public AdvancedRemote(Device device) {
        super(device);
    }

    public void setVolume(int level) {
        device.setVolume(level);
    }
}
