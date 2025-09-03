package LLD.DesignPatterns.Structural.Decorator;

public abstract class PizzaDecorator implements Pizza {
    protected Pizza pizza; // wrapped component

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }
}

