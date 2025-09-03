package LLD;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

// ------------------- Model -------------------
class Alarm {
    private final String id;
    private final String userId;
    private final LocalDateTime time;
    private final String label;
    private boolean isActive;

    public Alarm(String id, String userId, LocalDateTime time, String label) {
        this.id = id;
        this.userId = userId;
        this.time = time;
        this.label = label;
        this.isActive = true;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getLabel() {
        return label;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setInactive() {
        this.isActive = false;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", time=" + time +
                ", label='" + label + '\'' +
                ", active=" + isActive +
                '}';
    }
}

// ------------------- Repository -------------------
class AlarmRepository {
    private final Map<String, Alarm> store = new ConcurrentHashMap<>();

    public void save(Alarm alarm) {
        store.put(alarm.getId(), alarm);
    }

    public Alarm get(String id) {
        return store.get(id);
    }

    public void delete(String id) {
        store.remove(id);
    }

    public List<Alarm> getAll() {
        return store.values().stream().collect(Collectors.toList());
    }
}

// ------------------- Service -------------------
class AlarmService {
    private final AlarmRepository repo;

    public AlarmService(AlarmRepository repo) {
        this.repo = repo;
    }

    public String createAlarm(String userId, LocalDateTime time, String label) {
        String id = UUID.randomUUID().toString();
        Alarm alarm = new Alarm(id, userId, time, label);
        repo.save(alarm);
        return id;
    }

    public void updateAlarm(String id, LocalDateTime newTime, String newLabel) {
        Alarm old = repo.get(id);
        if (old != null) {
            Alarm updated = new Alarm(id, old.getUserId(), newTime, newLabel);
            repo.save(updated);
        }
    }

    public Alarm getAlarm(String id) {
        return repo.get(id);
    }

    public void deleteAlarm(String id) {
        repo.delete(id);
    }

    public Collection<Alarm> getAllAlarms() {
        return repo.getAll();
    }
}

// ------------------- Polling Thread -------------------
class AlarmPoller implements Runnable {
    private final AlarmService service;

    public AlarmPoller(AlarmService service) {
        this.service = service;
    }

    @Override
    public void run() {
        while (true) {
            LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
            for (Alarm alarm : service.getAllAlarms()) {
                if (alarm.isActive() && alarm.getTime().equals(now)) {
                    System.out.println("⏰ [TRIGGER] Alarm: " + alarm.getLabel() + " (User: " + alarm.getUserId() + ")");
                    alarm.setInactive(); // prevent repeated trigger
                }
            }

            try {
                Thread.sleep(60_000); // Sleep for 1 minute
            } catch (InterruptedException e) {
                System.out.println("Poller interrupted.");
                break;
            }
        }
    }
}

// ------------------- Main App -------------------
public class AlarmSystemApp {
    public static void main(String[] args) {
        AlarmRepository repo = new AlarmRepository();
        AlarmService service = new AlarmService(repo);

        // Set an alarm to trigger one minute from now
        LocalDateTime time = LocalDateTime.now().plusMinutes(1).withSecond(0).withNano(0);
        String alarmId = service.createAlarm("user123", time, "Wake up!");

        System.out.println("✅ Alarm created: " + service.getAlarm(alarmId));

        // Start background polling
        Thread poller = new Thread(new AlarmPoller(service));
        poller.setDaemon(true); // ensures JVM can exit
        poller.start();

        // Keep main thread alive for 3 minutes to observe
        try {
            Thread.sleep(3 * 60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("App ended.");
    }
}

