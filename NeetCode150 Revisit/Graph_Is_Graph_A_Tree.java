package ReviseAgain;

public class Graph_Is_Graph_A_Tree {
    public static void main(String[] args) {
        Graph_Is_Graph_A_Tree sol = new Graph_Is_Graph_A_Tree();

        int[][] edges1 = {
                {0, 1}, {0, 2}, {0, 3}, {1, 4}
        };
        System.out.println("Is valid tree? " + sol.validTree(5, edges1)); // true

        int[][] edges2 = {
                {0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}
        };
        System.out.println("Is valid tree? " + sol.validTree(5, edges2)); // false (cycle)
    }

    private boolean validTree(int n, int[][] edges) {
        if (n != edges.length - 1) {
            return false;
        }
        int[] parent = new int[n];
        // Initialize each node as its own parent
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            int rootU = find(parent, u);
            int rootV = find(parent, v);

            if (rootU == rootV) {
                // Found a cycle
                return false;
            }

            // Union the sets
            parent[rootU] = rootV;
        }
        return true;

    }

    private int find(int[] parent, int node) {
        while (parent[node] != node) {
            parent[node] = parent[parent[node]]; // Path compression
            node = parent[node];
        }
        return node;
    }

}
