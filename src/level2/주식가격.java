package level2;

import java.util.ArrayDeque;
import java.util.Arrays;

public class 주식가격 {
  public static void main(String[] args) {
    int[] prices = { 1, 2, 3, 2, 3 };
    System.out.println(Arrays.toString(solution(prices)));
  }

  public static int[] solution(int[] prices) {
    ArrayDeque<int[]> q = new ArrayDeque<>();
    q.add(new int[] { prices[0], 0 });

    int[] ans = new int[prices.length];

    int idx = 1;
    while (!q.isEmpty()) {
      while (!q.isEmpty() && idx < prices.length && q.peekLast()[0] > prices[idx]) {
        int[] p = q.pollLast();
        ans[p[1]] = idx - p[1];
      }

      if (idx < prices.length) {
        q.offer(new int[] { prices[idx], idx });
        idx++;
      } else {
        while (!q.isEmpty()) {
          int[] p = q.poll();
          ans[p[1]] = idx - p[1] - 1;
        }
      }
    }

    return ans;
  }
}
