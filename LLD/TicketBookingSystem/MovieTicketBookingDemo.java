package LLD.TicketBookingSystem;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

enum SeatStatus {
    AVAILABLE, BOOKED
}

class Seat {
    private final String seatNumber;
    private SeatStatus status;
    private final Lock lock = new ReentrantLock();

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
        this.status = SeatStatus.AVAILABLE;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public boolean tryBook() {
        lock.lock();
        try {
            if (status == SeatStatus.AVAILABLE) {
                status = SeatStatus.BOOKED;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }
}

class Theater {
    final String theaterId;
    private final String name;
    private final List<Seat> seats;

    public Theater(String theaterId, String name, int numberOfSeats) {
        this.theaterId = theaterId;
        this.name = name;
        this.seats = new ArrayList<>();
        for (int i = 1; i <= numberOfSeats; i++) {
            seats.add(new Seat("S" + i));
        }
    }

    public List<Seat> getSeats() {
        return seats;
    }
}

class Movie {
    final String movieId;
    private final String title;

    public Movie(String movieId, String title) {
        this.movieId = movieId;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

class Show {
    final String showId;
    private final Movie movie;
    private final Theater theater;
    private final Date showTime;
    final Map<String, Seat> seatMap;

    public Show(String showId, Movie movie, Theater theater, Date showTime) {
        this.showId = showId;
        this.movie = movie;
        this.theater = theater;
        this.showTime = showTime;
        this.seatMap = new HashMap<>();
        for (Seat seat : theater.getSeats()) {
            seatMap.put(seat.getSeatNumber(), seat);
        }
    }

    public boolean bookSeat(String seatNumber) {
        Seat seat = seatMap.get(seatNumber);
        if (seat == null) return false;
        return seat.tryBook();
    }
}

class Booking {
    private final String bookingId;
    private final Show show;
    private final List<Seat> bookedSeats;
    private final String userId;

    public Booking(String bookingId, Show show, List<Seat> bookedSeats, String userId) {
        this.bookingId = bookingId;
        this.show = show;
        this.bookedSeats = bookedSeats;
        this.userId = userId;
    }

    public String getBookingId() {
        return bookingId;
    }
}

class BookingSystem {
    private final Map<String, Movie> movies = new HashMap<>();
    private final Map<String, Theater> theaters = new HashMap<>();
    private final Map<String, Show> shows = new HashMap<>();
    private final Map<String, Booking> bookings = new ConcurrentHashMap<>();

    public void addMovie(Movie movie) {
        movies.put(movie.movieId, movie);
    }

    public void addTheater(Theater theater) {
        theaters.put(theater.theaterId, theater);
    }

    public void addShow(Show show) {
        shows.put(show.showId, show);
    }

    public boolean bookTicket(String showId, String seatNumber, String userId) {
        Show show = shows.get(showId);
        if (show == null) return false;

        boolean success = show.bookSeat(seatNumber);
        if (success) {
            Seat seat = show.seatMap.get(seatNumber);
            Booking booking = new Booking(UUID.randomUUID().toString(), show, List.of(seat), userId);
            bookings.put(booking.getBookingId(), booking);
            System.out.println("Booking successful for user " + userId + " seat " + seatNumber);
            return true;
        } else {
            System.out.println("Booking failed for user " + userId + " seat " + seatNumber + " (already booked)");
            return false;
        }
    }
}

public class MovieTicketBookingDemo {
    public static void main(String[] args) throws InterruptedException {
        // Setup system
        BookingSystem system = new BookingSystem();

        Movie movie = new Movie("M1", "Avengers");
        Theater theater = new Theater("T1", "IMAX", 10);
        Show show = new Show("S1", movie, theater, new Date());

        system.addMovie(movie);
        system.addTheater(theater);
        system.addShow(show);

        // Simulate concurrent booking for the same seat "S1"
        int threadCount = 5;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        String seatToBook = "S1";

        for (int i = 1; i <= threadCount; i++) {
            final String userId = "User" + i;
            executor.submit(() -> {
                system.bookTicket("S1", seatToBook, userId);
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }
}
