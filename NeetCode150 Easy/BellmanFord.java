package NeetCode150;

import java.util.*;

public class BellmanFord {

    public static void main(String[] args) {
        int n = 4;
        int[][] flights = {
                {0, 1, 100},
                {1, 2, 100},
                {2, 3, 100},
                {0, 3, 500}
        };
        int src = 0;
        int dst = 3;
        int K = 1;

        BellmanFord solution = new BellmanFord();
        int cheapestPrice = solution.findCheapestPrice(n, flights, src, dst, K);

        System.out.println("Cheapest price from " + src + " to " + dst + " with up to " + K + " stops is: " + cheapestPrice);
}

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i = 0; i <= K; i++) { // iterate over all the eges... because K+1 number of edges honge....
            int[] tempDist = dist.clone();
            for (int[] flight : flights) {
                int u = flight[0], v = flight[1], w = flight[2];
                if (dist[u] == Integer.MAX_VALUE) continue;
                if (dist[u] + w < tempDist[v]) {
                    tempDist[v] = dist[u] + w;
                }
            }
            dist = tempDist;
        }
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }


    public int findCheapestPriceUsingDijkstra(int n, int[][] flights, int src, int dst, int K) {
        // Build adjacency list: city -> List of (neighbor, price)
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] f : flights) {
            graph.get(f[0]).add(new int[]{f[1], f[2]});
        }

        // Min-heap: {cost, city, stops}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, src, 0});

        // Track minimum stops to reach a city to avoid unnecessary work
        int[] stopsTo = new int[n];
        Arrays.fill(stopsTo, Integer.MAX_VALUE);
        stopsTo[src] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int cost = curr[0], city = curr[1], stops = curr[2];

            if (city == dst) return cost;
            if (stops > K) continue;

            for (int[] nei : graph.get(city)) {
                int nextCity = nei[0], price = nei[1];
                int nextStops = stops + 1;

                // If we reached nextCity with fewer stops, explore
                if (nextStops <= K + 1 && nextStops < stopsTo[nextCity]) {
                    stopsTo[nextCity] = nextStops;
                    pq.offer(new int[]{cost + price, nextCity, nextStops});
                }
            }
        }

        return -1;
    }
}
