package NeetCode150;

import java.util.ArrayList;
import java.util.List;

public class Graph_Connected_Components {

    public static void main(String[] args) {
        Graph_Connected_Components sol = new Graph_Connected_Components();

        int[][] edges1 = {
                {0, 1}, {1, 2}, {3, 4}
        };
        System.out.println("Components: " + sol.countComponents(5, edges1)); // Output: 2

        int[][] edges2 = {
                {0, 1}, {1, 2}, {2, 3}, {3, 4}
        };
        System.out.println("Components: " + sol.countComponents(5, edges2)); // Output: 1
    }

    private int countComponents(int n, int[][] edges) {

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Build undirected graph
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, graph, visited);
                count++; // Found a new connected component
            }
        }

        return count;
    }

    private void dfs(int node, List<List<Integer>> graph, boolean[] visited) {
        visited[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited);
            }
        }
    }


}
