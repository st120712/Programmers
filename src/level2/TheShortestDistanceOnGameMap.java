package level2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class TheShortestDistanceOnGameMap {

    public static void main(String[] args) {
        int[][] maps = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}};

        System.out.println(solution(maps));
    }

    public static int solution(int[][] maps) {
        int r = maps.length;
        int c = maps[0].length;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});

        int[][] visited = new int[r][c];
        for (int i = 0; i < r; i++) {
            Arrays.fill(visited[i], -1);
        }
        visited[0][0] = 1;

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int cr = cur[0];
            int cc = cur[1];

            for (int[] d : directions) {
                int nr = cr + d[0];
                int nc = cc + d[1];

                if (nr < 0 || nr >= r || nc < 0 || nc >= c || maps[nr][nc] == 0 || visited[nr][nc] != -1) {
                    continue;
                }

                visited[nr][nc] = visited[cr][cc] + 1;

                if (nr == r - 1 && nc == c - 1) {
                    return visited[nr][nc];
                }
                q.offer(new int[]{nr, nc});
            }
        }

        return -1;
    }
}
