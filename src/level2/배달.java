package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class 배달 {

    public static void main(String[] args) {
        int N = 6;
        int[][] road = {{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}};
        int K = 4;

        System.out.println(solution(N, road, K));
    }

    public static int solution(int N, int[][] road, int K) {
        List<int[]>[] list = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int[] r : road) {
            int duration = r[2];
            int u = r[0];
            int v = r[1];

            list[u].add(new int[]{v, duration});
            list[v].add(new int[]{u, duration});
        }

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        Queue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        q.offer(new int[]{0, 1});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int d = cur[0];
            int u = cur[1];
            if (d > dist[u]) {
                continue;
            }
            for (int[] nx : list[u]) {
                int v = nx[0];
                int w = nx[1];
                int nd = d + w;
                if (nd < dist[v]) {
                    dist[v] = nd;
                    q.offer(new int[]{nd, v});
                }
            }
        }

        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                cnt++;
            }
        }

        return cnt;
    }
}
