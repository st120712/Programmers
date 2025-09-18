package practice;

public class LCS길이계산하기 {
  public static void main(String[] args) {
    String str1 = "ABCBDAB";
    String str2 = "BDCAB";
    System.out.println(solution(str1, str2));
  }

  public static int solution(String str1, String str2) {
    int r = str1.length();
    int c = str2.length();
    int[][] dp = new int[r + 1][c + 1];

    for (int i = 1; i <= r; i++) {
      for (int j = 1; j <= c; j++) {
        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
        }
      }
    }

    return dp[r][c];
  }
}
