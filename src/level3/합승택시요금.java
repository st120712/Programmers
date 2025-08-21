package level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class 합승택시요금 {

    public static void main(String[] args) {
        int n = 6;
        int s = 4;
        int a = 6;
        int b = 2;
        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};

        System.out.println(solution(n, s, a, b, fares));
    }

    public static class Edge {

        int to;
        int w;

        public Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }

        public int getTo() {
            return to;
        }

        public int getW() {
            return w;
        }
    }

    static final long INF = Long.MAX_VALUE;

    public static int solution(int n, int s, int a, int b, int[][] fares) {
        // 인접리스트 생성
        List<Edge>[] g = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] fare : fares) {
            int u = fare[0];
            int v = fare[1];
            int w = fare[2];

            g[u].add(new Edge(v, w));
            g[v].add(new Edge(u, w));
        }

        long[] distS = dijkstra(n, s, g);
        long[] distA = dijkstra(n, a, g);
        long[] distB = dijkstra(n, b, g);

        long ans = INF;
        for (int k = 1; k <= n; k++) {
            if (distS[k] == INF || distA[k] == INF || distB[k] == INF) {
                continue;
            }
            long cost = distS[k] + distA[k] + distB[k];
            if (ans > cost) {
                ans = cost;
            }
        }

        return (int) ans;
    }

    public static long[] dijkstra(int n, int start, List<Edge>[] g) {
        long[] dist = new long[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.offer(new long[]{0, start});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int v = (int) cur[1];

            // lazy skip!
            if (d != dist[v]) {
                continue;
            }

            for (Edge e : g[v]) {
                long nd = d + e.getW();
                if (dist[e.getTo()] > nd) {
                    dist[e.getTo()] = nd;
                    pq.offer(new long[]{nd, e.getTo()});
                }
            }
        }

        return dist;
    }
}
