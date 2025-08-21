package level2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class 전력망을둘로나누기 {

    public static void main(String[] args) {
        int n = 9;
        int[][] wires = {{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}};

        System.out.println(solution(n, wires));
    }

    public static int solution(int n, int[][] wires) {
        int ans = Integer.MAX_VALUE;

        List<Integer>[] g = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] wire : wires) {
            int u = wire[0];
            int v = wire[1];

            g[u].add(v);
            g[v].add(u);
        }

        boolean[] visited = new boolean[n + 1];
        visited[1] = true;

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);

        for (int i = 0; i < wires.length; i++) {
            int count = 1;
            int[] removingEdge = wires[i];

            while (!q.isEmpty()) {
                int u = q.poll();

                for (int v : g[u]) {
                    if ((u == removingEdge[0] && v == removingEdge[1]) || (u == removingEdge[1] && v == removingEdge[0])) {
                        continue;
                    }

                    if (visited[v] == false) {
                        q.offer(v);
                        count++;
                        visited[v] = true;
                    }
                }
            }

            int abs = Math.abs(n - (2 * count));
            if (abs == 0) {
                return 0;
            }
            if (ans > abs) {
                ans = abs;
            }
            q.offer(1);
            Arrays.fill(visited, false);
            visited[1] = true;
        }

        return ans;
    }
}
