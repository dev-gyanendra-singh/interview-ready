package LLD.DesignPatterns.Creational.FactoryDesign;

public class FactoryDesignDemo {
    public static void main(String[] args) {
        FactoryManager factory = new FactoryManager();

        Shape shape1 = factory.getShape("circle");
        shape1.draw();

        Shape shape2 = factory.getShape("square");
        shape2.draw();
    }
}
