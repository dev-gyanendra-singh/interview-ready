package LLD.DesignPatterns.Structural.Facade;

public class Computer {
    private CPU cpu = new CPU();
    private Memory memory = new Memory();
    private HardDrive hardDrive = new HardDrive();

    public void start() {
        cpu.freeze();
        String data = hardDrive.read(100, 512);
        memory.load(0, data);
        cpu.execute();
    }
}