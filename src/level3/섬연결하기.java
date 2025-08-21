package level3;

import java.util.Arrays;
import java.util.Comparator;

public class 섬연결하기 {

    public static void main(String[] args) {
        int n = 4;
        int[][] costs = {{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}};

        System.out.println(solution(n, costs));
    }

    public static int solution(int n, int[][] costs) {
        Arrays.sort(costs, Comparator.comparingInt(a -> a[2]));
        DSU dsu = new DSU(n);
        int used = 0;
        int ans = 0;

        for (int[] e : costs) {
            int u = e[0];
            int v = e[1];
            int w = e[2];
            if (dsu.union(u, v)) {
                ans += w;
                if (++used == n - 1) {
                    break;
                }
            }
        }

        return ans;
    }

    public static class DSU {

        private int[] parent;
        private int[] rank;

        public DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean union(int a, int b) {
            a = find(a);
            b = find(b);

            if (a == b) {
                return false;
            }
            if (rank[a] < rank[b]) {
                parent[a] = b;
            } else if (rank[a] > rank[b]) {
                parent[b] = a;
            } else {
                parent[b] = a;
                rank[a]++;
            }

            return true;
        }
    }
}
