package LLD.MovingTicketBookingSystem;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MovieBookingShowDemo {

    public static void main(String[] args) {
        // Setup
        Movie movie = new Movie("Inception", 148, "English");
        Theater theater = new Theater("PVR", "Downtown");
        Screen screen = new Screen(1);
        Show show = new Show(movie, new Date(), 20); // 20 seats
        screen.addShow(show);
        theater.addScreen(screen);

        // User interaction
        User user = new User("Alice");
        System.out.println("Available seats:");
        for (Seat seat : show.getAvailableSeats()) {
            System.out.print(seat.getSeatNumber() + " ");
        }
        System.out.println();

        // Simulate booking
        List<Integer> seatNumbersToBook = Arrays.asList(1, 2, 3);
        Booking booking = user.bookTickets(show, seatNumbersToBook);

        // Re-show available seats
        System.out.println("Available seats after booking:");
        for (Seat seat : show.getAvailableSeats()) {
            System.out.print(seat.getSeatNumber() + " ");
        }
    }
}
