package level3;

import java.util.Arrays;

public class 단속카메라 {
  public static void main(String[] args) {

  }

  public static int solution(int[][] routes) {
    Arrays.sort(routes, (a, b) -> a[1] - b[1]);

    int d = -30_000;
    int cnt = 0;

    for (int[] r : routes) {
      if (r[0] <= d && r[1] >= d) {
        continue;
      }

      cnt++;
      d = r[1];
    }

    return cnt;
  }
}
