package level3;

import java.util.HashSet;
import java.util.Set;

public class N으로표현 {
  public static void main(String[] args) {
    int N = 5;
    int number = 12;

    System.out.println(solution(N, number));
  }

  public static int solution(int N, int number) {
    if (N == number) {
      return 1;
    }

    Set<Integer>[] dp = new HashSet[9];
    for (int i = 0; i < dp.length; i++) {
      dp[i] = new HashSet<>();
    }
    dp[1].add(N);

    for (int i = 2; i < dp.length; i++) {
      for (int j = 1; j <= i / 2; j++) {
        Set<Integer> s = operate(N, i, dp[j], dp[i - j]);
        if (s.contains(number)) {
          return i;
        }
        dp[i].addAll(s);
      }
    }

    return -1;
  }

  public static Set<Integer> operate(int N, int len, Set<Integer> set1, Set<Integer> set2) {
    Set<Integer> set3 = new HashSet<>();

    for (int n1 : set1) {
      for (int n2 : set2) {
        set3.add(n2 + n1);
        set3.add(n1 * n2);
        set3.add(n2 - n1);
        set3.add(n1 - n2);
        if (n2 != 0)
          set3.add(n1 / n2);
        if (n1 != 0)
          set3.add(n2 / n1);
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < len; i++) {
      sb.append(N);
    }
    set3.add(Integer.parseInt(sb.toString()));

    return set3;
  }
}
