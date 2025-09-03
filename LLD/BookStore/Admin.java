package LLD.BookStore;

public class Admin extends User {
    Admin(String id, String name, String email, String password) {
        super(id, name, email, password);
    }

    public void addBook(Book book, Inventory inventory) {
        inventory.addBook(book);
    }
}
