package LLD.MeetingScheduler;

import java.util.List;

class Meeting {
    int startTime; // e.g. 900 for 9:00 AM
    int endTime;   // e.g. 1000 for 10:00 AM
    List<User> participants;

    public Meeting(int startTime, int endTime, List<User> participants) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.participants = participants;
    }

    public boolean overlaps(Meeting other) {
        return this.startTime < other.endTime && other.startTime < this.endTime;
    }

    @Override
    public String toString() {
        return "Meeting[" + startTime + " - " + endTime + "]";
    }
}
