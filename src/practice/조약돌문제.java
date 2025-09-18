package practice;

public class 조약돌문제 {
    public static void main(String[] args) {
        int[][] arr = { { 1, 7, 13, 2, 6 }, { 2, -4, 2, 5, 4 }, { 5, 3, 5, -3, 1 } };
        System.out.println(solution(arr));
    }

    public static int solution(int[][] arr) {
        int n = arr[0].length;

        int[][] dp = new int[4][n];
        dp[0][0] = arr[0][0];
        dp[1][0] = arr[1][0];
        dp[2][0] = arr[2][0];
        dp[3][0] = arr[0][0] + arr[2][0];

        for (int i = 1; i < n; i++) {
            dp[0][i] = arr[0][i] + Math.max(dp[1][i - 1], dp[2][i - 1]);
            dp[1][i] = arr[1][i] + Math.max(Math.max(dp[0][i - 1], dp[2][i - 1]), dp[3][i - 1]);
            dp[2][i] = arr[2][i] + Math.max(dp[0][i - 1], dp[1][i - 1]);
            dp[3][i] = arr[0][i] + arr[2][i] + dp[1][i - 1];
        }

        return Math.max(Math.max(dp[0][n - 1], dp[1][n - 1]), Math.max(dp[2][n - 1], dp[3][n - 1]));
    }
}
