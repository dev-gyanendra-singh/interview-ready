package LLD.MovingTicketBookingSystem;

public class Movie {
    String movieName;
    double duration;
    private String language;

    public Movie(String movieName, int duration, String language) {
        this.movieName = movieName;
        this.duration = duration;
        this.language = language;
    }

    public String getTitle() {
        return movieName;
    }
}
