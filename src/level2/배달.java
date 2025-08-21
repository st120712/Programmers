package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class 배달 {

    public static void main(String[] args) {
        int N = 6;
        int[][] road = {{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}};
        int K = 4;

        System.out.println(solution(N, road, K));
    }

    public static int solution(int N, int[][] road, int K) {
        // 양방향 인접 리스트 생성
        List<int[]>[] g = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            g[i] = new ArrayList<>();
        }
        // 최단거리를 구하는 문제이므로 각 노드에서 걸리는 시간도 같이 넣어줌 : 다익스트라 알고리즘
        for (int[] r : road) {
            int u = r[0];
            int v = r[1];
            int w = r[2];

            g[u].add(new int[]{v, w});
            g[v].add(new int[]{u, w});
        }

        // 첫 노드에서 해당 번째 노드까지 걸리는 최소 시간을 저장해놓는 배열
        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);
        // 이 문제는 1번 노드부터 시작하므로 1번 노드의 값은 0
        dist[1] = 0;

        // {d, u} 걸린 시간과 현재 노드를 저장
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        // 시작 노드를 넣음
        pq.offer(new int[]{0, 1});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0];
            int u = cur[1];
            // 걸린 시간이 이전에 걸린 시간 보다 크면 스킵
            if (d > dist[u]) {
                continue;
            }
            for (int[] l : g[u]) {
                int w = l[1];
                int v = l[0];

                if (dist[v] == -1 || dist[v] > d + w) {
                    dist[v] = d + w;
                    pq.offer(new int[]{dist[v], v});
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
