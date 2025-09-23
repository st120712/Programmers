package test.test3;

import java.util.Arrays;

public class Exam3 {

    public static void main(String[] args) {
        String[][] places = {{"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}};
        System.out.println(Arrays.toString(solution(places)));

    }

    static int[] solution(String[][] places) {
        int n = places.length;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            if (keepDistance(places[i])) {
                ans[i] = 1;
            }
        }

        return ans;
    }

    static boolean keepDistance(String[] place) {
        char[][] arr = new char[5][5];
        for (int i = 0; i < 5; i++) {
            arr[i] = place[i].toCharArray();
        }

        boolean keep = true;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                char c = arr[i][j];

                if (c == 'P') {
                    keep = dfs(0, arr, i, j, new boolean[5][5]);
                }
                if (!keep) {
                    break;
                }
            }
            if (!keep) {
                break;
            }
        }

        return keep;
    }

    static boolean dfs(int depth, char[][] arr, int x, int y, boolean[][] visited) {
        if (depth > 2) {
            return true;
        }

        if (arr[x][y] == 'X') {
            return true;
        }

        if (depth > 0 && arr[x][y] == 'P') {
            return false;
        }

        if (depth == 2) {
            return true;
        }

        visited[x][y] = true;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) {
                continue;
            }

            if (visited[nx][ny]) {
                continue;
            }

            if (!dfs(depth + 1, arr, nx, ny, visited)) {
                visited[x][y] = false;
                return false;
            }
        }

        visited[x][y] = false;
        return true;
    }
}
