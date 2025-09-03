package LLD.MovingTicketBookingSystem;

import java.util.*;

public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public Booking bookTickets(Show show, List<Integer> seatNumbers) {
        List<Seat> bookedSeats = new ArrayList<>();
        for (int seatNum : seatNumbers) {
            Seat seat = show.getSeatList().get(seatNum - 1);
            if (seat.bookSeat()) {
                bookedSeats.add(seat);
            } else {
                System.out.println("Seat " + seatNum + " is already booked.");
            }
        }
        if (!bookedSeats.isEmpty()) {
            return new Booking(this, show, bookedSeats);
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
