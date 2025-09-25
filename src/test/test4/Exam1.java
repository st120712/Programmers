package test.test4;

import java.util.Arrays;

public class Exam1 {

    public static void main(String[] args) {
        int alp = 0;
        int cop = 0;
        int[][] problems = {{0, 0, 2, 1, 2}, {4, 5, 3, 1, 2}, {4, 11, 4, 0, 2}, {10, 4, 0, 4, 2}};
        System.out.println(solution(alp, cop, problems));
    }

    static int solution(int alp, int cop, int[][] problems) {
        int INF = 1_000_000_000;

        int A = alp;
        int C = cop;
        for (int[] problem : problems) {
            A = Math.max(A, problem[0]);
            C = Math.max(C, problem[1]);
        }
        alp = Math.min(A, alp);
        cop = Math.min(C, cop);

        int[][] dp = new int[A + 1][C + 1];
        for (int i = 0; i <= A; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[alp][cop] = 0;

        for (int i = alp; i <= A; i++) {
            for (int j = cop; j <= C; j++) {
                if (i + 1 <= A) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }
                if (j + 1 <= C) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }
                for (int[] p : problems) {
                    int ar = p[0], cr = p[1], arw = p[2], crw = p[3], cost = p[4];
                    if (i >= ar && j >= cr) {
                        int ni = Math.min(A, i + arw);
                        int nj = Math.min(C, j + crw);
                        dp[ni][nj] = Math.min(dp[ni][nj], dp[i][j] + cost);
                    }
                }
            }
        }

        return dp[A][C];
    }
}
