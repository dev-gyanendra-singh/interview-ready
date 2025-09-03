package LLD.MeetingScheduler;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MeetingSchedulerDemo {

    public static void main(String[] args) {
        User alice = new User("Alice");
        User bob = new User("Bob");

        MeetingScheduler scheduler = new MeetingScheduler();
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 500000; i++) {
            executor.execute(createRunnable(scheduler, Arrays.asList(alice, bob)));
        }
    }
    private static Runnable createRunnable(MeetingScheduler scheduler, List<User> users) {
        return () -> {
            boolean flag = scheduler.scheduleMeeting(930, 1030, users);
            System.out.println("Meeting scheduled: " + flag);
            for (int i = 0; i < users.size(); i++) {
                scheduler.printSchedule(users.get(i));
            }
        };
    }
}
