package level2;

import java.util.LinkedList;
import java.util.Queue;

public class 미로탈출 {

    public static void main(String[] args) {
        String[] maps = {"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"};

        System.out.println(solution(maps));
    }

    public static int solution(String[] maps) {
        int r = maps.length;
        int c = maps[0].length();

        char[][] charMaps = new char[r][c];
        int[] start = new int[2];
        int[] lever = new int[2];

        for (int i = 0; i < r; i++) {
            if (maps[i].contains("S")) {
                start[0] = i;
                start[1] = maps[i].indexOf('S');
            }
            if (maps[i].contains("L")) {
                lever[0] = i;
                lever[1] = maps[i].indexOf('L');
            }
            charMaps[i] = maps[i].toCharArray();
        }

        int startToLever = calculateTime(start, 'L', r, c, charMaps);
        int leverToExit = calculateTime(lever, 'E', r, c, charMaps);

        if (startToLever == -1 || leverToExit == -1) {
            return -1;
        }

        return startToLever + leverToExit;
    }

    public static int calculateTime(int[] start, char end, int r, int c, char[][] charMaps) {
        Queue<int[]> q = new LinkedList<>();
        q.add(start);

        int[][] visited = new int[r][c];

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!q.isEmpty()) {

            int[] cur = q.poll();
            int cr = cur[0];
            int cc = cur[1];

            for (int[] d : directions) {
                int nr = cr + d[0];
                int nc = cc + d[1];

                if (nr < 0 || nr >= r || nc < 0 || nc >= c || charMaps[nr][nc] == 'X' || visited[nr][nc] != 0) {
                    continue;
                }

                visited[nr][nc] = visited[cr][cc] + 1;

                if (charMaps[nr][nc] == end) {
                    return visited[nr][nc];
                }

                q.add(new int[]{nr, nc});
            }
        }

        return -1;
    }
}
