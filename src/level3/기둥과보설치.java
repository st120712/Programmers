package level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 기둥과보설치 {

    public static void main(String[] args) {
        // [x 좌표 / y 좌표 / 0 : 기둥, 1 : 보 / 0 : 제거, 1 : 설치]
        int[][] build_frame = {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}};
        int n = 5;

        System.out.println(Arrays.deepToString(solution(n, build_frame)));
    }

    public static int[][] solution(int n, int[][] build_frame) {
        boolean[][] pillar = new boolean[n + 2][n + 2];
        boolean[][] beam = new boolean[n + 2][n + 2];

        for (int[] cmd : build_frame) {
            int x = cmd[0], y = cmd[1], a = cmd[2], b = cmd[3];

            if (b == 1) {
                if (a == 0) {
                    pillar[x][y] = true;
                    if (!isAvailable(n, pillar, beam)) {
                        pillar[x][y] = false;
                    }
                } else {
                    beam[x][y] = true;
                    if (!isAvailable(n, pillar, beam)) {
                        beam[x][y] = false;
                    }
                }
            } else {
                if (a == 0) {
                    pillar[x][y] = false;
                    if (!isAvailable(n, pillar, beam)) {
                        pillar[x][y] = true;
                    }
                } else {
                    beam[x][y] = false;
                    if (!isAvailable(n, pillar, beam)) {
                        beam[x][y] = true;
                    }
                }
            }
        }

        List<int[]> out = new ArrayList<>();
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                if (pillar[x][y]) {
                    out.add(new int[]{x, y, 0});
                }
                if (beam[x][y]) {
                    out.add(new int[]{x, y, 1});
                }
            }
        }

        out.sort((a, b) -> {
            if (a[0] == b[0]) {
                if (a[1] == b[1]) {
                    return Integer.compare(a[2], b[2]);
                } else {
                    return Integer.compare(a[1], b[1]);
                }
            }
            return Integer.compare(a[0], b[0]);
        });

        int[][] ans = new int[out.size()][3];
        for (int i = 0; i < out.size(); i++) {
            ans[i] = out.get(i);
        }

        return ans;
    }

    static boolean isAvailable(int n, boolean[][] pillar, boolean[][] beam) {
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                if (pillar[x][y] && !canInstallPillar(x, y, pillar, beam)) {
                    return false;
                }
                if (beam[x][y] && !canInstallBeam(x, y, pillar, beam, n)) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean canInstallPillar(int x, int y, boolean[][] pillar, boolean[][] beam) {
        if (y == 0) {
            return true;
        }
        if (y > 0 && pillar[x][y - 1]) {
            return true;
        }
        if (beam[x][y]) {
            return true;
        }
        if (x > 0 && beam[x - 1][y]) {
            return true;
        }
        return false;
    }

    static boolean canInstallBeam(int x, int y, boolean[][] pillar, boolean[][] beam, int n) {
        boolean supportedByLeftPillar = (y > 0) && pillar[x][y - 1];
        boolean supportedByRightPillar = (y > 0) && (x + 1 <= n) && pillar[x + 1][y - 1];
        if (supportedByLeftPillar || supportedByRightPillar) {
            return true;
        }

        boolean connectedBothSides = (x > 0) && (x + 1 <= n) && beam[x - 1][y] && beam[x + 1][y];
        return connectedBothSides;
    }
}
