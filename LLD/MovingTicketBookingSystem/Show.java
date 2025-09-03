package LLD.MovingTicketBookingSystem;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Show {
    List<Seat> seatList;
    private Movie movie;
    private Date showTime;

    Show(Movie movie, Date showTime, int seatCount) {
        this.movie = movie;
        this.showTime = showTime;
        seatList = new ArrayList<>();
        for (int i = 0; i < seatCount; i++) {
            seatList.add(new Seat(i));
        }
    }

    public Movie getMovie() {
        return movie;
    }

    public Date getShowTime() {
        return showTime;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> availableSeatList = new ArrayList<>();
        for (Seat seat : seatList) {
            if (seat.getStatus() == SeatStatus.AVAILABLE) {
                availableSeatList.add(seat);
            }
        }
        return availableSeatList;
    }
}
