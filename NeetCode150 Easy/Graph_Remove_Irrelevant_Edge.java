package NeetCode150;

import java.util.Arrays;

public class Graph_Remove_Irrelevant_Edge {
    public static void main(String[] args) {
        Graph_Remove_Irrelevant_Edge sol = new Graph_Remove_Irrelevant_Edge();

        int[][] edges = {
                {1, 2}, {1, 3}, {2, 3}
        };

        int[] result = sol.findRedundantConnection(edges);
        System.out.println("Redundant edge: " + Arrays.toString(result)); // Output: [2, 3]
    }

    private int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];  // 1-based indexing

        // Initialize: each node is its own parent
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            int rootU = find(parent, u);
            int rootV = find(parent, v);

            if (rootU == rootV) {
                // Found a cycle — this is the redundant edge
                return edge;
            }

            // Union
            parent[rootU] = rootV;
        }

        return new int[0];

    }

    int find(int[] parent, int v) {
        if (parent[v] != v) {
            parent[v] = parent[parent[v]];
            v = parent[v];
        }
        return v;
    }
}
