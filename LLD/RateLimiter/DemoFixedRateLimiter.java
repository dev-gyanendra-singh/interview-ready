package LLD.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DemoFixedRateLimiter {

    public static void main(String[] args) throws InterruptedException {
        FixedWindowRateLimiter limiter = new FixedWindowRateLimiter(5, 10_000); // 5 requests per 10 seconds
        String userId = "user123";

        for (int i = 0; i < 20; i++) {
            boolean allowed = limiter.allowRequest(userId);
            System.out.println("Request " + i + ": " + (allowed ? "Allowed" : "Blocked"));
            Thread.sleep(1000);
        }
    }
}

class FixedWindowRateLimiter {
    private final int maxRequests;
    private final long windowSizeMillis;
    private final ConcurrentHashMap<String, Window> userWindows = new ConcurrentHashMap<>();

    public FixedWindowRateLimiter(int maxRequests, long windowSizeMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeMillis = windowSizeMillis;
    }

    public boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();
        Window window = userWindows.computeIfAbsent(userId, k -> new Window(currentTime));

        synchronized (window) {
            if (currentTime - window.windowStart >= windowSizeMillis) {
                window.windowStart = currentTime;
                window.requestCount.set(0);
            }

            if (window.requestCount.incrementAndGet() <= maxRequests) {
                return true;
            } else {
                return false;
            }
        }
    }

    private static class Window {
        long windowStart;
        AtomicInteger requestCount;

        Window(long start) {
            this.windowStart = start;
            this.requestCount = new AtomicInteger(0);
        }
    }
}
