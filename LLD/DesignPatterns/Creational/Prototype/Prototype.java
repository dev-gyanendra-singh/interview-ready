package LLD.DesignPatterns.Creational.Prototype;

public class Prototype {

    private Prototype() {

    }

   public static Prototype getInstance() {
        return new Prototype();
    }
}
