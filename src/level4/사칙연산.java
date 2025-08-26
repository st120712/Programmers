package level4;

import java.util.ArrayList;
import java.util.List;

public class 사칙연산 {
  public static void main(String[] args) {
    String[] arr = { "1", "-", "3", "+", "5", "-", "8" };
    System.out.println(solution(arr));
  }

  public static int solution(String[] arr) {
    List<Integer> nums = new ArrayList<>();
    List<Character> ops = new ArrayList<>();
    for (int i = 0; i < arr.length; i++) {
      if (i % 2 == 0) {
        nums.add(Integer.parseInt(arr[i]));
      } else {
        ops.add(arr[i].charAt(0));
      }
    }

    int n = nums.size();
    int[][] max = new int[n][n];
    int[][] min = new int[n][n];

    for (int i = 0; i < n; i++) {
      max[i][i] = nums.get(i);
      min[i][i] = nums.get(i);
    }

    for (int len = 2; len <= n; len++) {
      for (int i = 0; i + len - 1 < n; i++) {
        int j = i + len - 1;
        int curMax = Integer.MIN_VALUE;
        int curMin = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {
          char op = ops.get(k);
          if (op == '+') {
            curMax = Math.max(curMax, max[i][k] + max[k + 1][j]);
            curMin = Math.min(curMin, min[i][k] + min[k + 1][j]);
          } else {
            curMax = Math.max(curMax, max[i][k] - min[k + 1][j]);
            curMin = Math.min(curMin, min[i][k] - max[k + 1][j]);
          }
        }
        max[i][j] = curMax;
        min[i][j] = curMin;
      }
    }

    return max[0][n - 1];
  }
}
