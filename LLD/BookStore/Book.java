package LLD.BookStore;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private String description;
    private double price;
    private int stock;

    public Book(String number, String effectiveJava, String joshuaBloch, String javaBestPractices, double v, int i) {
        isbn = number;
        title = effectiveJava;
        author = joshuaBloch;
        description = javaBestPractices;
        price = v;
        stock = i;
    }

    public void reduceStock(int qty) { stock -= qty; }
    public boolean isAvailable(int qty) { return stock >= qty; }

    public String getIsbn() { return isbn; }

    public String getTitle() { return title; }

    public double getPrice() {
        return price;
    }
    public String getAuthor() { return author; }
    public String getDescription() { return description; }
    public int getStock() { return stock; }

}
