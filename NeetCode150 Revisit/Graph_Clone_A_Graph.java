package ReviseAgain;

import java.util.*;

public class Graph_Clone_A_Graph {
    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int val) {
            this.val = val;
            neighbors = new ArrayList<>();
        }

        public Node(int val, List<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }
    }

    public static void main(String[] args) {
        // Test graph:
        // 1 -- 2
        // |    |
        // 4 -- 3

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors.addAll(Arrays.asList(node2, node4));
        node2.neighbors.addAll(Arrays.asList(node1, node3));
        node3.neighbors.addAll(Arrays.asList(node2, node4));
        node4.neighbors.addAll(Arrays.asList(node1, node3));

        Graph_Clone_A_Graph sol = new Graph_Clone_A_Graph();
        Node cloned = sol.cloneGraph(node1);

        System.out.println("Graph cloned. Root val: " + cloned.val); // Should print 1
    }

    Map<Node, Node> map = new HashMap<>();

    private Node cloneGraph(Node root) {
        if(root == null) return null;
        if(map.containsKey(root)) return map.get(root);
        Node clone = new Node(root.val);
        map.put(root, clone);
        for(Node neighbor : root.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }
        return clone;
    }
}
