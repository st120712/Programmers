package level2;

import java.util.Arrays;

public class 땅따먹기 {

    public static void main(String[] args) {
        int[][] land = {{1, 2, 3, 5}, {5, 6, 7, 8}, {4, 3, 2, 1}};

        System.out.println(solution(land));
    }

    public static int solution(int[][] land) {
        int r = land.length;
        int c = land[0].length;

        int[][] dp = new int[r][c];
        dp[0] = Arrays.copyOf(land[0], c);

        for (int i = 1; i < r; i++) {
            for (int j = 0; j < c; j++) {
                for (int k = 0; k < c; k++) {
                    if (j == k) {
                        continue;
                    }
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + land[i][j]);
                }
            }
        }

        return Arrays.stream(dp[r - 1]).max().getAsInt();
    }
}
