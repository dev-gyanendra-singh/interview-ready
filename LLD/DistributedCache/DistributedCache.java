package LLD.DistributedCache;

import java.util.*;
import java.util.concurrent.*;

public class DistributedCache {

    private final int capacity;
    private final Map<String, String> cache;
    private final LinkedHashSet<String> accessOrder;
    private final List<DistributedCache> replicaNodes;

    public DistributedCache(int capacity) {
        this.capacity = capacity;
        this.cache = new ConcurrentHashMap<>();
        this.accessOrder = new LinkedHashSet<>();
        this.replicaNodes = new ArrayList<>();
    }

    // Simulate replication to other nodes (eventual consistency)
    public void addReplicaNode(DistributedCache replica) {
        if (!replica.equals(this)) {
            replicaNodes.add(replica);
        }
    }

    // Get a value from cache
    public synchronized String get(String key) {
        if (cache.containsKey(key)) {
            accessOrder.remove(key);
            accessOrder.add(key);
            return cache.get(key);
        }
        return null;
    }

    // Put a key-value pair in the cache
    public synchronized void put(String key, String value) {
        if (cache.containsKey(key)) {
            accessOrder.remove(key);
        } else if (cache.size() >= capacity) {
            evict();
        }

        cache.put(key, value);
        accessOrder.add(key);

        // Replicate to other nodes asynchronously
        for (DistributedCache replica : replicaNodes) {
            CompletableFuture.runAsync(() -> replica.putReplica(key, value));
        }
    }

    // Internal use only: don't replicate further to avoid infinite loop
    private synchronized void putReplica(String key, String value) {
        if (cache.containsKey(key)) {
            accessOrder.remove(key);
        } else if (cache.size() >= capacity) {
            evict();
        }
        cache.put(key, value);
        accessOrder.add(key);
    }

    // LRU Eviction
    private synchronized void evict() {
        Iterator<String> it = accessOrder.iterator();
        if (it.hasNext()) {
            String evictKey = it.next();
            it.remove();
            cache.remove(evictKey);
            System.out.println("Evicted: " + evictKey);
        }
    }

    // Debugging helper
    public synchronized void printCacheState() {
        System.out.println("Cache Contents: " + cache);
    }

    // Example usage
    public static void main(String[] args) {
        DistributedCache node1 = new DistributedCache(3);
        DistributedCache node2 = new DistributedCache(3);

        node1.addReplicaNode(node2);
        node2.addReplicaNode(node1);

        node1.put("txn:1", "result_1");
        node1.put("txn:2", "result_2");
        node1.put("txn:3", "result_3");
        node1.put("txn:4", "result_4"); // triggers eviction

        System.out.println("Node 1:");
        node1.printCacheState();

        System.out.println("Node 2 (eventual):");
        node2.printCacheState();

        System.out.println("Read txn:2 from Node 2: " + node2.get("txn:2"));
    }
}

/*
* import java.util.*;

interface EvictionPolicy<K> {
    void keyAccessed(K key);
    K evictKey();
}

class LruEvictionPolicy<K> implements EvictionPolicy<K> {
    private final LinkedHashMap<K, Boolean> accessOrder =
        new LinkedHashMap<>(16, 0.75f, true); // access-order

    @Override
    public void keyAccessed(K key) { accessOrder.put(key, true); }

    @Override
    public K evictKey() {
        Iterator<K> it = accessOrder.keySet().iterator();
        if (it.hasNext()) { K lru = it.next(); it.remove(); return lru; }
        return null;
    }
}

class CacheNode<K, V> {
    private final int capacity;
    private final Map<K, V> store = new HashMap<>();
    private final EvictionPolicy<K> evictionPolicy;

    CacheNode(int capacity, EvictionPolicy<K> evictionPolicy) {
        this.capacity = capacity; this.evictionPolicy = evictionPolicy;
    }

    public synchronized V get(K key) {
        if (store.containsKey(key)) evictionPolicy.keyAccessed(key);
        return store.get(key);
    }

    public synchronized void put(K key, V value) {
        if (store.size() >= capacity && !store.containsKey(key)) {
            K evict = evictionPolicy.evictKey();
            if (evict != null) store.remove(evict);
        }
        store.put(key, value);
        evictionPolicy.keyAccessed(key);
    }
}

class CacheCluster<K, V> {
    private final List<CacheNode<K, V>> nodes;

    CacheCluster(List<CacheNode<K, V>> nodes) { this.nodes = nodes; }

    private int getNodeIndex(K key) {
        return Math.abs(key.hashCode()) % nodes.size(); // simple hashing
    }

    public V get(K key) { return nodes.get(getNodeIndex(key)).get(key); }
    public void put(K key, V value) { nodes.get(getNodeIndex(key)).put(key, value); }
}

public class Main {
    public static void main(String[] args) {
        CacheNode<String, String> n1 = new CacheNode<>(2, new LruEvictionPolicy<>());
        CacheNode<String, String> n2 = new CacheNode<>(2, new LruEvictionPolicy<>());
        CacheCluster<String, String> cluster = new CacheCluster<>(List.of(n1, n2));

        cluster.put("a", "apple");
        cluster.put("b", "banana");
        cluster.put("c", "cherry"); // may evict depending on hash

        System.out.println(cluster.get("a")); // may be null if evicted
        System.out.println(cluster.get("b")); // "banana"
    }
}
*/

