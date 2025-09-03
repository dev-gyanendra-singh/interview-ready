package LLD.RateLimiter;

public class TokenBucketRateLimiter {
    private final long capacity;             // Max tokens in the bucket
    private final long refillRate;           // Tokens added per second
    private double tokens;                   // Current number of tokens
    private long lastRefillTimestamp;        // Last time tokens were refilled

    public TokenBucketRateLimiter(long capacity, long refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = capacity; // Start full
        this.lastRefillTimestamp = System.nanoTime();
    }

    public synchronized boolean allowRequest() {
        refill();

        if (tokens >= 1) {
            tokens -= 1;
            return true; // Allow the request
        } else {
            return false; // Reject the request
        }
    }

    private void refill() {
        long now = System.nanoTime();
        double tokensToAdd = ((now - lastRefillTimestamp) / 1e9) * refillRate;
        if (tokensToAdd > 0) {
            tokens = Math.min(capacity, tokens + tokensToAdd);
            lastRefillTimestamp = now;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(5, 2); // 5 tokens max, refill 2/sec

        for (int i = 0; i < 10; i++) {
            System.out.println("Request " + i + ": " + limiter.allowRequest());
            Thread.sleep(300); // simulate some delay between requests
        }
    }
}

