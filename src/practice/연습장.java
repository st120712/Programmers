package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 연습장 {

    public static void main(String[] args) {
        int k = 4;
        int[][] operations = {{0, 0, 1}, {1, 1, 2}, {0, 1, 2}, {1, 0, 2}};
        System.out.println(Arrays.toString(solution(k, operations)));
    }

    public static boolean[] solution(int k, int[][] operations) {
        int[] parent = new int[k];
        int[] rank = new int[k];

        for (int i = 0; i < k; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        List<Boolean> list = new ArrayList<>();

        for (int[] op : operations) {
            if (op[0] == 0) {
                union(op[1], op[2], rank, parent);
            } else {
                int a = find(op[1], parent);
                int b = find(op[2], parent);
                list.add(a == b);
            }
        }

        boolean[] ans = new boolean[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    public static int find(int x, int[] parent) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x], parent);
    }

    public static void union(int a, int b, int[] rank, int[] parent) {
        a = find(a, parent);
        b = find(b, parent);
        if (a == b) {
            return;
        }
        if (rank[a] > rank[b]) {
            parent[b] = a;
        } else if (rank[a] < rank[b]) {
            parent[a] = b;
        } else {
            parent[b] = a;
            rank[a]++;
        }
    }
}
