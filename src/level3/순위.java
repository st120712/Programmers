package level3;

public class 순위 {

    public static void main(String[] args) {

    }

    public static int solution(int n, int[][] results) {
        boolean[][] win = new boolean[n + 1][n + 1];
        for (int[] r : results) {
            int w = r[0];
            int l = r[1];

            win[w][l] = true;
        }

        for (int k = 1; k < n + 1; k++) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    if (win[i][k] && win[k][j]) {
                        win[i][j] = true;
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 1; i < n + 1; i++) {
            int wins = 0;
            int loses = 0;

            for (int j = 1; j < n + 1; j++) {
                if (win[i][j]) {
                    wins++;
                }

                if (win[j][i]) {
                    loses++;
                }
            }

            if (wins + loses == n - 1) {
                ans++;
            }
        }

        return ans;
    }
}
