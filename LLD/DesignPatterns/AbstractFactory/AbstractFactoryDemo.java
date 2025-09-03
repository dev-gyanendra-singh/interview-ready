package LLD.DesignPatterns.AbstractFactory;

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        GUIFactory factory;

        String os = "windows"; // or "mac"

        if (os.equalsIgnoreCase("windows")) {
            factory = new WindowsFactory();
        } else {
            factory = new MacFactory();
        }

        Application app = new Application(factory);
        app.renderUI();
    }
}
