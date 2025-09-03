package LLD.BookStoreEasy;

import java.util.*;

public class LibraryDemo {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("123", "Clean Code", "Robert C. Martin");
        book1.addCopy(new BookCopy("copy1", book1));
        book1.addCopy(new BookCopy("copy2", book1));

        library.addBook(book1);

        User user1 = new User("u1", "Alice");
        library.addUser(user1);

        boolean borrowed = library.borrowBook("u1", "123");
        System.out.println("Borrowed book? " + borrowed);  // true

        boolean returned = library.returnBook("u1", "copy1");
        System.out.println("Returned book? " + returned);  // true
    }
}

class Book {
    private String isbn;
    private String title;
    private String author;
    private List<BookCopy> copies;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.copies = new ArrayList<>();
    }

    public String getIsbn() { return isbn; }

    public void addCopy(BookCopy copy) {
        copies.add(copy);
    }

    public List<BookCopy> getAvailableCopies() {
        List<BookCopy> available = new ArrayList<>();
        for (BookCopy copy : copies) {
            if (copy.isAvailable()) {
                available.add(copy);
            }
        }
        return available;
    }
}

class BookCopy {
    private String copyId;
    private Book book;
    private boolean isAvailable;

    public BookCopy(String copyId, Book book) {
        this.copyId = copyId;
        this.book = book;
        this.isAvailable = true;
    }

    public String getCopyId() { return copyId; }
    public boolean isAvailable() { return isAvailable; }
    public void borrow() { isAvailable = false; }
    public void returnCopy() { isAvailable = true; }
}

class User {
    private String userId;
    private String name;
    List<BookCopy> borrowedCopies;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.borrowedCopies = new ArrayList<>();
    }

    public String getUserId() { return userId; }
    public void borrowCopy(BookCopy copy) {
        borrowedCopies.add(copy);
        copy.borrow();
    }

    public void returnCopy(BookCopy copy) {
        borrowedCopies.remove(copy);
        copy.returnCopy();
    }
}

class Library {
    private Map<String, Book> books;
    private Map<String, User> users;

    public Library() {
        books = new HashMap<>();
        users = new HashMap<>();
    }

    public void addBook(Book book) { books.put(book.getIsbn(), book); }
    public void addUser(User user) { users.put(user.getUserId(), user); }

    public boolean borrowBook(String userId, String isbn) {
        User user = users.get(userId);
        Book book = books.get(isbn);
        if (user == null || book == null) return false;

        List<BookCopy> availableCopies = book.getAvailableCopies();
        if (availableCopies.isEmpty()) return false;

        BookCopy copy = availableCopies.get(0);
        user.borrowCopy(copy);
        return true;
    }

    public boolean returnBook(String userId, String copyId) {
        User user = users.get(userId);
        if (user == null) return false;

        for (BookCopy copy : new ArrayList<>(user.borrowedCopies)) {
            if (copy.getCopyId().equals(copyId)) {
                user.returnCopy(copy);
                return true;
            }
        }
        return false;
    }
}

