package test.test5;

public class Exam2 {

    public static void main(String[] args) {

    }

    static int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        int[][] diff = new int[n + 1][m + 1];

        for (int[] s : skill) {
            int type = s[0];
            int r1 = s[1], c1 = s[2], r2 = s[3], c2 = s[4];
            int degree = s[5];

            int val = type == 1 ? -degree : degree;

            diff[r1][c1] += val;
            diff[r1][c2 + 1] -= val;
            diff[r2 + 1][c1] -= val;
            diff[r2 + 1][c2 + 1] += val;
        }

        for (int r = 0; r <= n; r++) {
            for (int c = 1; c <= m; c++) {
                diff[r][c] += diff[r][c - 1];
            }
        }

        for (int c = 0; c <= m; c++) {
            for (int r = 1; r <= n; r++) {
                diff[r][c] += diff[r - 1][c];
            }
        }

        int alive = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                int finalHealth = board[r][c] + diff[r][c];
                if (finalHealth > 0) {
                    alive++;
                }
            }
        }

        return alive;
    }
}
