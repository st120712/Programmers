class Solution {
    public static int solution(int[][] land) {
    int r = land.length;
    int c = land[0].length;
    int[][] dp = new int[r][c];

    for (int i = 0; i < c; i++) {
      dp[0][i] = land[0][i];
    }

    for (int i = 1; i < r; i++) {
      for (int j = 0; j < c; j++) {
        for (int k = 0; k < c; k++) {
          if (k != j) {
            dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + land[i][j]);
          }
        }
      }
    }

    int ans = 0;

    for (int n : dp[r-1]) {
      if (ans < n) {
        ans = n;
      }
    }

    return ans;
  }
}