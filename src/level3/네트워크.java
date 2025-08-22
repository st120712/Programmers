package level3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 네트워크 {

    public static void main(String[] args) {
        int n = 3;
        int[][] computers = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};

        System.out.println(solution(n, computers));
    }

    public static int solution(int n, int[][] computers) {

        DSU dsu = new DSU(n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (computers[i][j] == 1) {
                    dsu.union(i, j);
                }
            }
        }

        Set<Integer> set = new HashSet<>();

        Arrays.stream(dsu.parent).forEach(a -> set.add(dsu.find(a)));

        return set.size();
    }

    public static class DSU {

        int[] parent;
        int[] rank;

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
            if (rank[a] > rank[b]) {
                parent[b] = a;
            } else if (rank[b] > rank[a]) {
                parent[a] = b;
            } else {
                parent[b] = a;
                rank[a]++;
            }

            return true;
        }
    }
}
