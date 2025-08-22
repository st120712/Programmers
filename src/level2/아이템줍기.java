package level2;

import java.util.ArrayDeque;
import java.util.Arrays;

public class 아이템줍기 {

    public static void main(String[] args) {
        int[][] rectangle = {{1, 1, 8, 4}, {2, 2, 4, 9}, {3, 6, 9, 8}, {6, 3, 7, 7}};
        int characterX = 9;
        int characterY = 7;
        int itemX = 6;
        int itemY = 1;

        System.out.println(solution(rectangle, characterX, characterY, itemX, itemY));
    }

    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int S = 2;
        int MAX = 51;
        int H = MAX * S;
        int W = MAX * S;

        int[][] map = new int[W + 1][H + 1];

        for (int[] r : rectangle) {
            int x1 = r[0] * S;
            int y1 = r[1] * S;
            int x2 = r[2] * S;
            int y2 = r[3] * S;
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    map[i][j] = 1;
                }
            }
        }

        for (int[] r : rectangle) {
            int x1 = r[0] * S;
            int y1 = r[1] * S;
            int x2 = r[2] * S;
            int y2 = r[3] * S;
            for (int i = x1 + 1; i <= x2 - 1; i++) {
                for (int j = y1 + 1; j <= y2 - 1; j++) {
                    map[i][j] = 0;
                }
            }
        }

        int sx = characterX * S;
        int sy = characterY * S;
        int tx = itemX * S;
        int ty = itemY * S;

        int[][] dist = new int[W + 1][H + 1];
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sx, sy});
        dist[sx][sy] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 1 && nx <= W && ny >= 1 && ny <= H && map[nx][ny] == 1 && dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[cx][cy] + 1;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        return dist[tx][ty] / S;
    }
}
