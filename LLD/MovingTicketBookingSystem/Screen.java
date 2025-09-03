package LLD.MovingTicketBookingSystem;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    int screenId;
    List<Show> showList;

    Screen(int screenId) {
        this.screenId = screenId;
        this.showList = new ArrayList<>();
    }

    void addShow(Show show) {
        showList.add(show);
    }
}
