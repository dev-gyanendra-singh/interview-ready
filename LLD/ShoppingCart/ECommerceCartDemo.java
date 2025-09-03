package LLD.ShoppingCart;

public class ECommerceCartDemo {
    public static void main(String[] args) {
        Product p1 = new Product("p1001", "Phone", 699.99);
        Product p2 = new Product("p1002", "Laptop", 1299.99);

        ShoppingCart cart = new ShoppingCart();

        cart.addItem(p1, 2);
        cart.addItem(p2, 1);

        System.out.println("Total Price: $" + cart.getTotalPrice());

        cart.updateItemQuantity("p1001", 1);
        System.out.println("Total Price after update: $" + cart.getTotalPrice());

        cart.removeItem("p1002");
        System.out.println("Total Price after removal: $" + cart.getTotalPrice());
    }
}
