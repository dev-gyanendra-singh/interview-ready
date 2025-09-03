package LLD.BookStore;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addItem(Book book, int quantity) {
        for (CartItem item : items) {
            if (item.getBook().getIsbn().equals(book.getIsbn())) {
                item.increaseQuantity(quantity);
                return;
            }
        }
        items.add(new CartItem(book, quantity));
    }

    public List<CartItem> getItems() { return items; }
    public double getTotal() {
        return items.stream().mapToDouble(i -> i.getBook().getPrice() * i.getQuantity()).sum();
    }
}
