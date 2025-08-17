package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 무인도여행 {

    public static void main(String[] args) {
        String[] maps = {"X591X", "X1X5X", "X231X", "1XXX1"};

        System.out.println(Arrays.toString(solution(maps)));
    }

    public static int[] solution(String[] maps) {
        int asciiZero = '0';

        int r = maps.length;
        int c = maps[0].length();

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[r][c];
        List<Integer> survivingDays = new ArrayList<>();
        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                char cur = maps[i].charAt(j);

                if (cur == 'X' || visited[i][j]) {
                    continue;
                }

                int survivingDay = cur - asciiZero;
                q.add(new int[]{i, j});
                visited[i][j] = true;

                while (!q.isEmpty()) {
                    int[] curPos = q.poll();
                    int cr = curPos[0];
                    int cc = curPos[1];

                    for (int[] d : direction) {
                        int nr = cr + d[0];
                        int nc = cc + d[1];

                        if (nr < 0 || nr >= r || nc < 0 || nc >= c || maps[nr].charAt(nc) == 'X' || visited[nr][nc]) {
                            continue;
                        }

                        visited[nr][nc] = true;
                        survivingDay += maps[nr].charAt(nc) - asciiZero;
                        q.add(new int[]{nr, nc});
                    }
                }

                survivingDays.add(survivingDay);
            }
        }

        if (survivingDays.isEmpty()) {
            return new int[]{-1};
        }

        return survivingDays.stream().sorted().mapToInt(Integer::intValue).toArray();
    }
}
