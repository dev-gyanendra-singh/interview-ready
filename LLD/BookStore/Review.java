package LLD.BookStore;

public class Review {
    private String userId;
    private String bookIsbn;
    private int rating;  // 1 to 5
    private String comment;

    public Review(String userId, String bookIsbn, int rating, String comment) {
        this.userId = userId;
        this.bookIsbn = bookIsbn;
        this.rating = rating;
        this.comment = comment;
    }
}
