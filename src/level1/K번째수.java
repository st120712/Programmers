package level1;

import java.util.Arrays;

public class K번째수 {
  public static void main(String[] args) {

  }

  public static int[] solution(int[] array, int[][] commands) {
    int[] ans = new int[commands.length];
    int ansSize = 0;

    for (int[] command : commands) {
      int start = command[0];
      int end = command[1];
      int k = command[2];

      int[] arr = new int[end - start + 1];
      int arrSize = 0;

      for (int i = start; i <= end; i++) {
        arr[arrSize++] = array[i - 1];
      }

      Arrays.sort(arr);
      ans[ansSize++] = arr[k - 1];
    }

    return ans;
  }
}
