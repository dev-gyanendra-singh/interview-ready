package LLD.DesignPatterns.Structural.Facade;

public class Memory {
    public void load(long position, String data) {
        System.out.println("Loading data to memory at " + position + ": " + data);
    }
}

