package LLD.DesignPatterns.Creational.FactoryDesign;

public class FactoryManager {
    public static Shape getShape(String type) {
        Shape shape = null;
        switch (type.toLowerCase()) {
            case "circle" -> shape = new Circle();
            case "square" -> shape = new Square() ;
            default -> throw new IllegalArgumentException("Unknown shape type: " + type);
        }
        return shape;
    }
}

