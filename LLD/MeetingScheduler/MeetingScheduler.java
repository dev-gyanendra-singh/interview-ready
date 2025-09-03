package LLD.MeetingScheduler;

import java.util.List;

class MeetingScheduler {
    public boolean scheduleMeeting(int startTime, int endTime, List<User> participants) {
        Meeting newMeeting = new Meeting(startTime, endTime, participants);
        for (User user : participants) {
            for (Meeting meeting : user.getMeetings()) {
                if (meeting.overlaps(newMeeting)) {
                    System.out.println("Conflict for user " + user.getName() + " with " + meeting);
                    return false;
                }
            }
        }

        // No conflicts; add meeting to all users
        for (User user : participants) {
            user.getMeetings().add(newMeeting);
        }
        return true;
    }

    public void printSchedule(User user) {
        System.out.println("Schedule for " + user.getName() + ":");
        for (Meeting meeting : user.getMeetings()) {
            System.out.println(meeting);
        }
    }
}
