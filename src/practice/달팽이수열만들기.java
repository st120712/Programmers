package practice;

import java.util.Arrays;

public class 달팽이수열만들기 {

    public static void main(String[] args) {
        int n = 4;
        int[][] ans = solution(n);
        for (int[] a : ans) {
            System.out.println(Arrays.toString(a));
        }
    }

    public static int[][] solution(int n) {
        int direction = 0;
        int[][] ans = new int[n][n];
        int r = 0;
        int c = 0;

        for (int i = 1; i <= n * n; i++) {
            ans[r][c] = i;

            while (i != n * n) {
                int nr = r;
                int nc = c;

                if (direction == 0) {
                    nc++;
                }
                if (direction == 1) {
                    nr++;
                }
                if (direction == 2) {
                    nc--;
                }
                if (direction == 3) {
                    nr--;
                }

                if (nr < 0 || nr >= n || nc < 0 || nc >= n || ans[nr][nc] != 0) {
                    direction = (direction + 1) % 4;
                } else {
                    r = nr;
                    c = nc;
                    break;
                }
            }
        }

        return ans;
    }
}
