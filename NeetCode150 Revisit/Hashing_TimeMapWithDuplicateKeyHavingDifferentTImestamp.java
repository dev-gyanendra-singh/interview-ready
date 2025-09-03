package ReviseAgain;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Hashing_TimeMapWithDuplicateKeyHavingDifferentTImestamp {
    public static void main(String[] args) {
        Hashing_TimeMapWithDuplicateKeyHavingDifferentTImestamp main = new Hashing_TimeMapWithDuplicateKeyHavingDifferentTImestamp(); // For instantiating inner class

        // Instantiate TimeMap
        TimeMap timeMap = main.new TimeMap();

        // Perform operations as per the input sequence
        timeMap.set("foo", "bar", 1);
        System.out.println(timeMap.get("foo", 1)); // Output: "bar"
        System.out.println(timeMap.get("foo", 3)); // Output: "bar"
        timeMap.set("foo", "bar2", 4);
        System.out.println(timeMap.get("foo", 4)); // Output: "bar2"
        System.out.println(timeMap.get("foo", 5)); // Output: "bar2"
    }

    class TimeMap {
        Map<String, TreeMap<Integer, String>> map;

        public TimeMap() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            map.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) {
                return "";
            }
            Integer subkey = map.get(key).floorKey(timestamp);
            if (subkey == null) {
                return "";
            }
            return map.get(key).get(subkey);
        }
    }
}
