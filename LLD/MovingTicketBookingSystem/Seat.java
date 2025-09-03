package LLD.MovingTicketBookingSystem;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Seat {
    private int seatNumber;
    private SeatStatus status;

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.status = SeatStatus.AVAILABLE;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public SeatStatus getStatus() {
        return status;
    }

    private final Lock lock = new ReentrantLock();

    public boolean bookSeat() {
        lock.lock(); // Acquire the lock
        try {
            if (status == SeatStatus.AVAILABLE) {
                status = SeatStatus.BOOKED;
                return true;
            }
            return false;
        } finally {
            lock.unlock(); // Always release the lock
        }
    }
}

