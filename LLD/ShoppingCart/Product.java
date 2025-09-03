package LLD.ShoppingCart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;

public class Product {
    private final String id;
    private final String name;
    private final double price;

    public Product(String id, String name, double price) {
        this.id = id; this.name = name; this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}

class MeetingScheduler {
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, List<Meeting>> userMeetings = new HashMap<>();
    public void addUser(String userId, String name) {
        User user = new User(userId, name);
        users.put(userId, user);
        userMeetings.put(userId, new ArrayList<>());
    }
    public boolean scheduleMeeting(String meetingId, TimeSlot slot,
                                   List<String> userIds) {
        List<User> participants = new ArrayList<>();
        for (String userId : userIds) {
            if (!users.containsKey(userId)) {
                System.out.println("User " + userId + " does not exist.");
                return false;}
            // Check conflicts
            for (Meeting m : userMeetings.get(userId)) {
                if (m.getTimeSlot().conflictsWith(slot)) {
                    System.out.println("Conflict for user: " +
                            users.get(userId).getName());
                    return false;}}
            participants.add(users.get(userId));
        }
        // No conflicts, schedule meeting
        Meeting meeting = new Meeting(meetingId, slot, participants);
        for (User user : participants) {
            userMeetings.get(user.getUserId()).add(meeting);
        }
        System.out.println("Meeting scheduled: " + meeting);
        return true;
    }
    public void printUserSchedule(String userId) {
        System.out.println("Meetings for " + users.get(userId).getName() + ":");
        for (Meeting m : userMeetings.get(userId)) {
            System.out.println("  " + m);
        }
    }
}
