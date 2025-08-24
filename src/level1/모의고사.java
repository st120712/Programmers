package level1;

import java.util.Arrays;

public class 모의고사 {
  public static void main(String[] args) {
    int[] answers = { 1, 3, 2, 4, 2 };
    System.out.println(Arrays.toString(solution(answers)));
  }

  public static int[] solution(int[] answers) {
    int[] ansB = { 1, 3, 4, 5 };
    int[] ansC = { 3, 1, 2, 4, 5 };
    int[] count = new int[3];
    int n = answers.length;
    for (int i = 0; i < n; i++) {
      int a = i % 5 + 1;
      int b = i % 2 == 0 ? 2 : ansB[((i - 1) / 2) % 4];
      int c = i % 2 == 0 ? ansC[((i + 1) / 2) % 5] : ansC[((i - 1) / 2) % 5];

      if (answers[i % n] == a) {
        count[0]++;
      }
      if (answers[i % n] == b) {
        count[1]++;
      }
      if (answers[i % n] == c) {
        count[2]++;
      }
    }

    int max = 0;
    for (int c : count) {
      max = Math.max(max, c);
    }

    int[] ans = new int[3];
    int size = 0;
    for (int i = 0; i < count.length; i++) {
      if (max == count[i]) {
        ans[size++] = i + 1;
      }
    }

    return Arrays.copyOf(ans, size);
  }
}
