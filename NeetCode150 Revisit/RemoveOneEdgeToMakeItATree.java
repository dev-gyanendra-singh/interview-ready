package ReviseAgain;

public class RemoveOneEdgeToMakeItATree {
    private int[] parent;

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        parent = new int[n + 1];

        // Initialize each node's parent to itself
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (!union(u, v)) {
                return edge;  // Found the redundant one
            }
        }

        return new int[0]; // Should never happen if input is valid
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // path compression
        }
        return parent[x];
    }

    private boolean union(int x, int y) {
        int rootX = find(x), rootY = find(y);
        if (rootX == rootY) return false; // cycle detected
        parent[rootX] = rootY;
        return true;
    }
}