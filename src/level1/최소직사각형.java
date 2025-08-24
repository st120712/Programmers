package level1;

import java.util.Arrays;

public class 최소직사각형 {
  public static void main(String[] args) {

  }

  public static int solution(int[][] sizes) {
    int[] max = new int[2];

    for (int[] size : sizes) {
      Arrays.sort(size);
      max[0] = Math.max(max[0], size[0]);
      max[1] = Math.max(max[1], size[1]);
    }

    return max[0] * max[1];
  }
}
