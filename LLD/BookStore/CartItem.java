package LLD.BookStore;

public class CartItem {
    private Book book;
    private int quantity;

    public CartItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public void increaseQuantity(int qty) { this.quantity += qty; }
    public Book getBook() { return book; }
    public int getQuantity() { return quantity; }
}
