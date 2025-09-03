package LLD.MeetingScheduler;

import java.util.*;

class User {
    String name;
    List<Meeting> meetings ;

    public User(String name) {
        this.name = name;
        this.meetings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }
}
