//package LLD.BookStore;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class Order {
//    private String orderId;
//    private List<OrderItem> items;
//    private double totalAmount;
//    private OrderStatus status;
//    private Payment payment;
//
//    public Order(List<CartItem> cartItems) {
//        this.items = cartItems.stream()
//                .map(ci -> new OrderItem(ci.getBook(), ci.getQuantity()))
//                .collect(Collectors.toList());
//        this.totalAmount = items.stream()
//                .mapToDouble(i -> i.getBook().getPrice() * i.getQuantity())
//                .sum();
//        this.status = OrderStatus.PENDING;
//    }
//
//    public void completeOrder(Payment payment) {
//        this.payment = payment;
//        this.status = OrderStatus.CONFIRMED;
//    }
//}
