package practice;

import java.util.Arrays;

public class LIS길이계산하기 {
  public static void main(String[] args) {
    int[] nums = { 1, 4, 2, 3, 1, 5, 7, 3 };
    System.out.println(solution(nums));
  }

  public static int solution(int[] nums) {
    int n = nums.length;

    int[] dp = new int[n];
    Arrays.fill(dp, 1);

    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[j] < nums[i]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }

    return Arrays.stream(dp).max().getAsInt();
  }
}
