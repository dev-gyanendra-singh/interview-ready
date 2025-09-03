package LLD.ShoppingCart;

import LLD.MeetingScheduler.MeetingParticipants;

import java.util.*;

public class Meeting {
    String meetingId;
    TimeSlot timeSlot;
    List<User> participantsList;
    public Meeting(String meetingId, TimeSlot timeSlot, List<User> participantsList) {
        this.meetingId = meetingId;
        this.timeSlot = timeSlot;
        this.participantsList = participantsList;
    }

    TimeSlot getTimeSlot() {
        return timeSlot;
    }
}
