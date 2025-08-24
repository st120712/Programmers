package level2;

import java.util.Arrays;

public class 가장큰수 {
  public static void main(String[] args) {

  }

  public static String solution(int[] numbers) {
    String[] strs = Arrays.stream(numbers).mapToObj(String::valueOf).toArray(String[]::new);

    Arrays.sort(strs, (a, b) -> (b + a).compareTo(a + b));

    if (strs[0].equals("0")) {
      return "0";
    }

    String ans = "";
    for (String s : strs) {
      ans += s;
    }
    return ans;
  }
}
