package level3;

public class 보행자천국 {

    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        int[][] cityMap = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        System.out.println(solution(m, n, cityMap));
    }

    static int MOD = 20170805;

    static int solution(int m, int n, int[][] cityMap) {
        int[][] down = new int[m][n];
        int[][] right = new int[m][n];

        down[0][0] = 1;
        right[0][0] = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (cityMap[r][c] == 1) {
                    down[r][c] = 0;
                    right[r][c] = 0;
                    continue;
                }
                if (r > 0 && cityMap[r - 1][c] != 1) {
                    if (cityMap[r - 1][c] == 2) {
                        down[r][c] = (down[r][c] + down[r - 1][c]) % MOD;
                    } else {
                        int add = (down[r - 1][c] + right[r - 1][c]) % MOD;
                        down[r][c] = (down[r][c] + add) % MOD;
                    }
                }
                if (c > 0 && cityMap[r][c - 1] != 1) {
                    if (cityMap[r][c - 1] == 2) {
                        right[r][c] = (right[r][c] + right[r][c - 1]) % MOD;
                    } else {
                        int add = (down[r][c - 1] + right[r][c - 1]) % MOD;
                        right[r][c] = (right[r][c] + add) % MOD;
                    }
                }
            }
        }

        return (down[m - 1][n - 1] + right[m - 1][n - 1]) % MOD;
    }
}
