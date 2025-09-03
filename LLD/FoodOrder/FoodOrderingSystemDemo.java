package LLD.FoodOrder;

import java.util.*;

enum OrderStatus {
    PLACED, PREPARING, OUT_FOR_DELIVERY, DELIVERED, CANCELLED
}

class User {
    private String userId;
    private String name;
    private String address;

    public User(String userId, String name, String address) {
        this.userId = userId;
        this.name = name;
        this.address = address;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getAddress() { return address; }
}

class MenuItem {
    private String itemId;
    private String name;
    private String description;
    private double price;
    private boolean isAvailable;

    public MenuItem(String itemId, String name, String description, double price) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isAvailable = true;
    }

    public String getItemId() { return itemId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}

class Restaurant {
    private String restaurantId;
    private String name;
    private String location;
    private List<MenuItem> menu;

    public Restaurant(String restaurantId, String name, String location) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.location = location;
        this.menu = new ArrayList<>();
    }

    public void addMenuItem(MenuItem item) {
        menu.add(item);
    }

    public List<MenuItem> getMenu() {
        return menu;
    }

    public String getName() { return name; }
}

class OrderItem {
    private MenuItem menuItem;
    private int quantity;

    public OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public double getPrice() {
        return menuItem.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return quantity + " x " + menuItem.getName() + " ($" + getPrice() + ")";
    }
}

class Order {
    private String orderId;
    private User user;
    private Restaurant restaurant;
    private List<OrderItem> items;
    private OrderStatus status;
    private double totalAmount;

    public Order(String orderId, User user, Restaurant restaurant, List<OrderItem> items) {
        this.orderId = orderId;
        this.user = user;
        this.restaurant = restaurant;
        this.items = items;
        this.status = OrderStatus.PLACED;
        this.totalAmount = calculateTotal();
    }

    private double calculateTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    String getOrderId() { return orderId; }

    public void updateStatus(OrderStatus newStatus) {
        this.status = newStatus;
        System.out.println("Order " + orderId + " status updated to " + newStatus);
    }

    public void printOrderDetails() {
        System.out.println("Order ID: " + orderId);
        System.out.println("User: " + user.getName());
        System.out.println("Restaurant: " + restaurant.getName());
        System.out.println("Items:");
        for (OrderItem item : items) {
            System.out.println("  " + item);
        }
        System.out.println("Total amount: $" + totalAmount);
        System.out.println("Status: " + status);
        System.out.println("-------------------------");
    }
}

class OrderService {
    private Map<String, Order> orders = new HashMap<>();
    private int orderCounter = 1;

    public Order placeOrder(User user, Restaurant restaurant, List<OrderItem> items) {
        String orderId = "ORD" + orderCounter++;
        Order order = new Order(orderId, user, restaurant, items);
        orders.put(orderId, order);
        System.out.println("Order placed successfully: " + orderId);
        return order;
    }

    public void updateOrderStatus(String orderId, OrderStatus status) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.updateStatus(status);
        } else {
            System.out.println("Order not found: " + orderId);
        }
    }

    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }
}

public class FoodOrderingSystemDemo {
    public static void main(String[] args) {
        // Create users
        User user1 = new User("u1", "Alice", "123 Main St");

        // Create restaurants and menu
        Restaurant rest1 = new Restaurant("r1", "Pizza Place", "Downtown");
        rest1.addMenuItem(new MenuItem("m1", "Margherita Pizza", "Classic cheese pizza", 12.99));
        rest1.addMenuItem(new MenuItem("m2", "Pepperoni Pizza", "Pepperoni and cheese", 14.99));

        Restaurant rest2 = new Restaurant("r2", "Burger Joint", "Uptown");
        rest2.addMenuItem(new MenuItem("m3", "Cheeseburger", "Beef burger with cheese", 9.99));
        rest2.addMenuItem(new MenuItem("m4", "Fries", "Crispy french fries", 3.99));

        // Create order service
        OrderService orderService = new OrderService();

        // User selects items to order
        List<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem(rest1.getMenu().get(0), 2)); // 2 Margherita pizzas
        items.add(new OrderItem(rest1.getMenu().get(1), 1)); // 1 Pepperoni pizza

        // Place order
        Order order = orderService.placeOrder(user1, rest1, items);

        // Print order details
        order.printOrderDetails();

        // Update order status
        orderService.updateOrderStatus(order.getOrderId(), OrderStatus.PREPARING);
        orderService.updateOrderStatus(order.getOrderId(), OrderStatus.OUT_FOR_DELIVERY);
        orderService.updateOrderStatus(order.getOrderId(), OrderStatus.DELIVERED);
    }
}
