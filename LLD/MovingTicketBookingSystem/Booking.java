package LLD.MovingTicketBookingSystem;

import java.util.*;

public class Booking {
    private User user;
    private Show show;
    private List<Seat> seats;
    private Date bookingTime;

    public Booking(User user, Show show, List<Seat> seats) {
        this.user = user;
        this.show = show;
        this.seats = seats;
        this.bookingTime = new Date();
        System.out.println("Booking successful for " + user.getName() + " on movie: " +
                show.getMovie().getTitle() + " for seats: " + getSeatNumbers());
    }

    private String getSeatNumbers() {
        List<Integer> nums = new ArrayList<>();
        for (Seat seat : seats) {
            nums.add(seat.getSeatNumber());
        }
        return nums.toString();
    }
}
