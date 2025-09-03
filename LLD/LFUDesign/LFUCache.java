package LLD.LFUDesign;

import java.util.*;

public class LFUCache {
    private final int capacity;
    private int minFreq;
    private Map<Integer, Node> keyNode;
    private Map<Integer, LinkedHashSet<Node>> freqMap;

    private class Node {
        int key, val, freq;
        Node(int k, int v) {
            key = k; val = v; freq = 1;
        }
    }

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.keyNode = new HashMap<>();
        this.freqMap = new HashMap<>();
    }

    public int get(int key) {
        if (!keyNode.containsKey(key)) return -1;
        Node node = keyNode.get(key);
        updateFreq(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (keyNode.containsKey(key)) {
            Node node = keyNode.get(key);
            node.val = value;
            updateFreq(node);
        } else {
            if (keyNode.size() == capacity) {
                // Evict least freq used & least recently used node
                LinkedHashSet<Node> nodes = freqMap.get(minFreq);
                Node evict = nodes.iterator().next();
                nodes.remove(evict);
                if (nodes.isEmpty()) freqMap.remove(minFreq);
                keyNode.remove(evict.key);
            }
            Node node = new Node(key, value);
            keyNode.put(key, node);
            freqMap.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(node);
            minFreq = 1;
        }
    }

    private void updateFreq(Node node) {
        int freq = node.freq;
        LinkedHashSet<Node> nodes = freqMap.get(freq);
        nodes.remove(node);
        if (nodes.isEmpty()) {
            freqMap.remove(freq);
            if (freq == minFreq) minFreq++;
        }
        node.freq++;
        freqMap.computeIfAbsent(node.freq, k -> new LinkedHashSet<>()).add(node);
    }
}

