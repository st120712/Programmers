package level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class 등산코스정하기 {

    public static void main(String[] args) {
        int n = 6;
        int[][] paths = {{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}};
        int[] gates = {1, 3};
        int[] summits = {5};

        System.out.println(Arrays.toString(solution(n, paths, gates, summits)));
    }

    public static class Edge {

        int v;
        int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public int getV() {
            return v;
        }

        public int getW() {
            return w;
        }
    }

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<Edge>[] g = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] path : paths) {
            int u = path[0];
            int v = path[1];
            int w = path[2];

            g[u].add(new Edge(v, w));
            g[v].add(new Edge(u, w));
        }

        boolean[] isGate = new boolean[n + 1];
        for (int x : gates) {
            isGate[x] = true;
        }
        boolean[] isSummit = new boolean[n + 1];
        for (int x : summits) {
            isSummit[x] = true;
        }

        final int INF = Integer.MAX_VALUE;
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, INF);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int g0 : gates) {
            intensity[g0] = 0;
            pq.offer(new int[]{0, g0});
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curI = cur[0];
            int u = cur[1];

            if (curI > intensity[u]) {
                continue;
            }
            if (isSummit[u]) {
                continue;
            }

            for (Edge e : g[u]) {
                int v = e.getV();
                int nextI = Math.max(curI, e.getW());
                if (intensity[v] > nextI) {
                    intensity[v] = nextI;
                    pq.offer(new int[]{nextI, v});
                }
            }
        }

        Arrays.sort(summits);
        int bestSummit = summits[0];
        int bestI = intensity[bestSummit];
        for (int s : summits) {
            if (intensity[s] < bestI) {
                bestSummit = s;
                bestI = intensity[s];
            }
        }

        return new int[]{bestSummit, bestI};
    }

}
