package LLD.RateLimiter;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowRateLimiter {
    private final int maxRequests;
    private final long timeWindowMillis;
    private final Deque<Long> requestTimestamps;

    public SlidingWindowRateLimiter(int maxRequests, long timeWindowMillis) {
        this.maxRequests = maxRequests;
        this.timeWindowMillis = timeWindowMillis;
        this.requestTimestamps = new LinkedList<>();
    }

    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();

        // Remove timestamps older than the window
        while (!requestTimestamps.isEmpty() && (now - requestTimestamps.peekFirst() > timeWindowMillis)) {
            requestTimestamps.pollFirst();
        }

        if (requestTimestamps.size() < maxRequests) {
            requestTimestamps.offerLast(now);
            return true; // Allow request
        }

        return false; // Reject request
    }

    public static void main(String[] args) throws InterruptedException {
        SlidingWindowRateLimiter limiter = new SlidingWindowRateLimiter(5, 10000); // 5 requests per 10 sec

        for (int i = 1; i <= 10; i++) {
            boolean allowed = limiter.allowRequest();
            System.out.println("Request " + i + ": " + (allowed ? "Allowed" : "Rejected"));
            Thread.sleep(1000); // 1 sec between requests
        }
    }
}
