package LLD.RateLimiter;

import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

class SlidingWindowCounter {
    private final int maxRequests;
    private final long windowSizeInMillis;
    private final int bucketCount;
    private final ConcurrentHashMap<String, Deque<Bucket>> userBuckets = new ConcurrentHashMap<>();

    static class Bucket {
        long timestamp;
        int count;
        Bucket(long timestamp, int count) {
            this.timestamp = timestamp;
            this.count = count;
        }
    }

    public SlidingWindowCounter(int maxRequests, long windowSizeInMillis, int bucketCount) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
        this.bucketCount = bucketCount;
    }

    public boolean allowRequest(String userId) {
        long now = System.currentTimeMillis();
        long slidingInternalVal = windowSizeInMillis / bucketCount; // keep this as a const

        Deque<Bucket> buckets = userBuckets.computeIfAbsent(userId, k -> new ConcurrentLinkedDeque<>());

        synchronized (buckets) {
            // Clean up old buckets
            while (!buckets.isEmpty() && now - buckets.peekFirst().timestamp >= windowSizeInMillis) {
                buckets.pollFirst();
            }

            int currentCount = buckets.stream().mapToInt(b -> b.count).sum();
            if (currentCount >= maxRequests) return false;

            // Add to latest bucket or create new
            if (!buckets.isEmpty() && now - buckets.peekLast().timestamp < slidingInternalVal) {
                buckets.peekLast().count++;
            } else {
                buckets.pollFirst();
                buckets.addLast(new Bucket(now, 1));
            }
            return true;
        }
    }
}

