//package LLD.BookStore;
//
//public class BookStoreDemo {
//
//    public static void main(String[] args) {
//        // Create Inventory and Books
//        Inventory inventory = new Inventory();
//        Book book1 = new Book("9780134685991", "Effective Java", "Joshua Bloch", "Java best practices", 50.0, 10);
//        Book book2 = new Book("9780596009205", "Head First Java", "Kathy Sierra", "Java for beginners", 40.0, 5);
//        inventory.addBook(book1);
//        inventory.addBook(book2);
//
//        // Create a Customer
//        Customer customer = new Customer("C001", "Alice", "alice@example.com", "password123");
//
//        // Browse Books
//        System.out.println("Browsing books:");
//        for (Book b : inventory.searchByTitle("Java")) {
//            System.out.println(b.getTitle() + " - $" + b.getPrice());
//        }
//
//        // Add books to cart
//        customer.addToCart(book1, 2); // 2 Effective Java
//        customer.addToCart(book2, 1); // 1 Head First Java
//
//        // Show cart total
//        System.out.println("\nCart Total: $" + customer.getCart().getTotal());
//
//        // Place Order
//        customer.placeOrder();
//8
//        // Show Order History
//        System.out.println("\nOrder History:");
//        for (Order order : customer.getOrderHistory()) {
//            System.out.println("Order total: $" + order.getTotalAmount() + " | Status: " + order.status);
//        }
//
//        // Leave a review
//        customer.writeReview(book1, 5, "Great for experienced Java developers!");
//
//        // Done
//        System.out.println("\nDemo completed.");
//    }
//}
