package LLD.BookStore;

import java.util.*;
import java.util.stream.Collectors;

public class Inventory {
    private Map<String, Book> booksByISBN = new HashMap<>();

    public void addBook(Book book) {
        booksByISBN.put(book.getIsbn(), book);
    }

    public Book searchByISBN(String isbn) {
        return booksByISBN.get(isbn);
    }

    public List<Book> searchByTitle(String title) {
        return booksByISBN.values().stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }
}

