package level3;

import java.util.ArrayList;
import java.util.List;

public class 양과늑대 {

    public static void main(String[] args) {
        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edge = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};
        System.out.println(solution(info, edge));
    }

    static int maxSheep = 0;
    static boolean[][][] visited; // visited[mask][sheep][wolf]

    public static int solution(int[] info, int[][] edges) {
        int N = info.length;
        List<Integer>[] tree = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            tree[e[0]].add(e[1]);
        }

        visited = new boolean[1 << N][N + 1][N + 1];

        dfs(1, 1, 0, info, tree);

        return maxSheep;
    }

    public static void dfs(int mask, int sheep, int wolf, int[] info, List<Integer>[] tree) {
        if (sheep <= wolf) {
            return;
        }

        maxSheep = Math.max(maxSheep, sheep);
        if (visited[mask][sheep][wolf]) {
            return;
        }
        visited[mask][sheep][wolf] = true;

        for (int i = 0; i < info.length; i++) {
            if ((mask & (1 << i)) != 0) {
                for (int next : tree[i]) {
                    if ((mask & (1 << next)) == 0) {
                        if (info[next] == 0) {
                            dfs(mask | (1 << next), sheep + 1, wolf, info, tree);
                        } else {
                            dfs(mask | (1 << next), sheep, wolf + 1, info, tree);
                        }
                    }
                }
            }
        }
    }
}
