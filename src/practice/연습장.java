package practice;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class 연습장 {

    public static void main(String[] args) {
        String[] maps = {"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"};
        System.out.println(solution(maps));
    }

    public static int solution(String[] maps) {
        int r = maps.length;
        int c = maps[0].length();
        int[] start = new int[2];
        int[] lever = new int[2];
        int[] exit = new int[2];
        for (int i = 0; i < r; i++) {
            char[] arr = maps[i].toCharArray();
            for (int j = 0; j < c; j++) {
                if (arr[j] == 'S') {
                    start = new int[]{i, j};
                }
                if (arr[j] == 'L') {
                    lever = new int[]{i, j};
                }
                if (arr[j] == 'E') {
                    exit = new int[]{i, j};
                }
            }
        }

        int sToL = bfs(start, lever, maps);
        if (sToL == -1) {
            return -1;
        }
        int lToE = bfs(lever, exit, maps);
        if (lToE == -1) {
            return -1;
        }

        return sToL + lToE;
    }

    public static int bfs(int[] from, int[] to, String[] maps) {
        int r = maps.length;
        int c = maps[0].length();
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(from);

        int[][] visited = new int[r][c];
        for (int[] v : visited) {
            Arrays.fill(v, -1);
        }
        visited[from[0]][from[1]] = 0;

        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cr = cur[0];
            int cc = cur[1];

            for (int i = 0; i < 4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];
                if (nr < 0 || nr >= r || nc < 0 || nc >= c || visited[nr][nc] != -1 || maps[nr].toCharArray()[nc] == 'X') {
                    continue;
                }
                visited[nr][nc] = visited[cr][cc] + 1;
                int[] next = new int[]{nr, nc};
                if (Arrays.equals(next, to)) {
                    return visited[nr][nc];
                }
                q.offer(next);
            }
        }

        return -1;
    }
}
