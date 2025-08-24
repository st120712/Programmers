package level2;

import java.util.Arrays;

public class HIndex {
  public static void main(String[] args) {
    int[] citations = { 3, 0, 6, 1, 5 };

    System.out.println(solution(citations));
  }

  public static int solution(int[] citations) {
    Arrays.sort(citations);

    int n = citations.length;
    int h = 0;

    for (int i = 0; i < n; i++) {
      h = Math.max(h, Math.min(citations[i], n - i));
    }

    return h;
  }
}
