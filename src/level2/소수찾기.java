package level2;

import java.util.HashSet;
import java.util.Set;

public class 소수찾기 {
  public static void main(String[] args) {

  }

  public static int solution(String numbers) {
    char[] digits = numbers.toCharArray();
    boolean[] used = new boolean[digits.length];
    Set<Integer> made = new HashSet<>();

    dfs(digits, used, new StringBuilder(), made);

    int ans = 0;
    for (int m : made) {
      if (isPrime(m)) {
        ans++;
      }
    }

    return ans;
  }

  public static void dfs(char[] digits, boolean[] used, StringBuilder cur, Set<Integer> out) {
    if (cur.length() > 0) {
      out.add(Integer.parseInt(cur.toString()));
    }
    if (cur.length() == digits.length) {
      return;
    }

    for (int i = 0; i < digits.length; i++) {
      if (used[i]) {
        continue;
      }
      used[i] = true;
      cur.append(digits[i]);
      dfs(digits, used, cur, out);
      cur.deleteCharAt(cur.length() - 1);
      used[i] = false;
    }
  }

  public static boolean isPrime(int n) {
    if (n < 2) {
      return false;
    }
    if (n == 2) {
      return true;
    }
    if (n % 2 == 0) {
      return false;
    }
    int r = (int) Math.sqrt(n);
    for (int d = 3; d <= r; d += 2) {
      if (n % d == 0) {
        return false;
      }
    }
    return true;
  }
}
