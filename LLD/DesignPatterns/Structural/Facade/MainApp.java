package LLD.DesignPatterns.Structural.Facade;

public class MainApp {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.start();  // Facade call
    }
}

