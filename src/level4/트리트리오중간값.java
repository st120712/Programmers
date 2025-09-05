package level4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class 트리트리오중간값 {

    public static void main(String[] args) {
        int n = 5;
        int[][] edge = {{1, 5}, {2, 5}, {3, 5}, {4, 5}};

        System.out.println(solution(n, edge));
    }

    public static int solution(int n, int[][] edges) {
        List<Integer>[] tree = buildTree(n, edges);
        int u = findLongestNode(1, tree);
        int v = findLongestNode(u, tree);

        int[] distU = findDistances(u, tree);
        int[] distV = findDistances(v, tree);

        int ans = 0;

        for (int i = 1; i < n + 1; i++) {
            if (i == u || i == v) {
                continue;
            }
            int temp = Math.max(distU[i], distV[i]);
            ans = Math.max(ans, temp);
        }

        return ans;
    }

    public static int findLongestNode(int start, List<Integer>[] tree) {
        int[] dist = findDistances(start, tree);

        int max = 0;

        for (int d : dist) {
            max = Math.max(max, d);
        }

        for (int i = 0; i < tree.length; i++) {
            if (dist[i] == max) {
                return i;
            }
        }

        return -1;
    }

    public static int[] findDistances(int start, List<Integer>[] tree) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);

        int[] arr = new int[tree.length];
        Arrays.fill(arr, -1);
        arr[start] = 0;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : tree[now]) {
                if (arr[next] == -1) {
                    arr[next] = arr[now] + 1;
                    q.offer(next);
                }
            }
        }

        return arr;
    }

    public static List<Integer>[] buildTree(int n, int[][] edges) {
        List<Integer>[] list = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            list[e[0]].add(e[1]);
            list[e[1]].add(e[0]);
        }

        return list;
    }
}
