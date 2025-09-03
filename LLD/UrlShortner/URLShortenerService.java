package LLD.UrlShortner;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class URLShortenerService {
    private final ConcurrentHashMap<String, String> shortToLongMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> longToShortMap = new ConcurrentHashMap<>();
    // Base62 characters
    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    // Encode UUID to base62 for shorter keys
    private String encodeBase62(UUID uuid) {
        long least = uuid.getLeastSignificantBits() & Long.MAX_VALUE;
        StringBuilder sb = new StringBuilder();
        while (least > 0) {
            int rem = (int) (least % 62);
            sb.append(BASE62.charAt(rem));
            least /= 62;
        }
        return sb.toString();
    }
    public String shortenUrl(String longUrl) {
        // Check if already shortened
        if (longToShortMap.containsKey(longUrl)) {
            return longToShortMap.get(longUrl);
        }
        String shortKey;
        String baseUrl = "https://sho.rt/";
        // Retry in case of UUID collision (very rare)
        do {
            UUID uuid = UUID.randomUUID();
            shortKey = encodeBase62(uuid);
        } while (shortToLongMap.containsKey(shortKey));
        shortToLongMap.put(shortKey, longUrl);
        longToShortMap.put(longUrl, shortKey);
        return baseUrl + shortKey;
    }
    public String resolveUrl(String shortUrl) {
        String shortKey = shortUrl.replace("https://sho.rt/", "");
        return shortToLongMap.getOrDefault(shortKey, "Not Found");
    }
}

