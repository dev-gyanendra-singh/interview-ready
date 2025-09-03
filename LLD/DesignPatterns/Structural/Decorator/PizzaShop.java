package LLD.DesignPatterns.Structural.Decorator;

public class PizzaShop {
    public static void main(String[] args) {
        Pizza pizza = new PlainPizza();                           // Base pizza
        pizza = new CheeseDecorator(pizza);                       // + Cheese
        pizza = new OliveDecorator(pizza);                        // + Olives
        pizza = new PepperoniDecorator(pizza);                    // + Pepperoni

        System.out.println("Order: " + pizza.getDescription());
        System.out.println("Total Cost: $" + pizza.getCost());
    }
}

