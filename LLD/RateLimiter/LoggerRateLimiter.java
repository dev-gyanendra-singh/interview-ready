package LLD.RateLimiter;


import java.util.HashMap;
import java.util.Map;

// log should be printed if not printed in last 10 minutes
public class LoggerRateLimiter {
    private Map<String, Integer> msgTimestampMap;
    public LoggerRateLimiter() {
        msgTimestampMap = new HashMap<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!msgTimestampMap.containsKey(message) || timestamp - msgTimestampMap.get(message) >= 10) {
            msgTimestampMap.put(message, timestamp);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        LoggerRateLimiter logger = new LoggerRateLimiter();

        System.out.println(logger.shouldPrintMessage(1, "foo"));  // true
        System.out.println(logger.shouldPrintMessage(2, "bar"));  // true
        System.out.println(logger.shouldPrintMessage(3, "foo"));  // false
        System.out.println(logger.shouldPrintMessage(11, "foo")); // true
    }

}
