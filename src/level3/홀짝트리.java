package level3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 홀짝트리 {

    public static void main(String[] args) {
        int[] nodes = {9, 15, 14, 7, 6, 1, 2, 4, 5, 11, 8, 10};
        int[][] edges = {{5, 14}, {1, 4}, {9, 11}, {2, 15}, {2, 5}, {9, 7}, {8, 1}, {6, 4}};
        System.out.println(Arrays.toString(solution(nodes, edges)));
    }

    public static int[] solution(int[] nodes, int[][] edges) {
        int n = nodes.length;
        Map<Integer, Integer> idx = new HashMap<>();
        for (int i = 0; i < n; i++) {
            idx.put(nodes[i], i);
        }

        int[] parent = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        int[] deg = new int[n];
        for (int[] e : edges) {
            int a = idx.get(e[0]);
            int b = idx.get(e[1]);
            deg[a]++;
            deg[b]++;
            union(a, b, parent, size);
        }

        int[] cnt0 = new int[n];
        int[] cnt1 = new int[n];

        for (int i = 0; i < n; i++) {
            int r = find(i, parent);
            int A = nodes[i] & 1;
            int P = deg[i] & 1;
            int C = A ^ P;
            if (C == 0) {
                cnt0[r]++;
            } else {
                cnt1[r]++;
            }
        }

        int origin = 0, reverse = 0;
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) {
                if (cnt0[i] == 1) {
                    origin++;
                }
                if (cnt1[i] == 1) {
                    reverse++;
                }
            }
        }

        return new int[]{origin, reverse};
    }

    public static int find(int x, int[] parent) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x], parent);
    }

    public static void union(int a, int b, int[] parent, int[] size) {
        a = find(a, parent);
        b = find(b, parent);
        if (a == b) {
            return;
        }
        if (size[a] < size[b]) {
            int t = a;
            a = b;
            b = t;
        }
        parent[b] = a;
        size[a] += size[b];
    }
}
