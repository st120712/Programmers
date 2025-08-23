package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 행렬테두리회전하기 {
  public static void main(String[] args) {
    int rows = 6;
    int columns = 6;
    int[][] queries = { { 2, 2, 5, 4 }, { 3, 3, 6, 6 }, { 5, 1, 6, 3 } };

    System.out.println(Arrays.toString(solution(rows, columns, queries)));
  }

  public static int[] solution(int rows, int columns, int[][] queries) {

    int[][] map = new int[rows + 1][columns + 1];
    for (int i = 1; i <= rows; i++) {
      for (int j = 1; j <= columns; j++) {
        map[i][j] = j + (i - 1) * rows;
      }
    }

    List<Integer> ans = new ArrayList<>();

    for (int[] q : queries) {
      int r1 = q[0];
      int c1 = q[1];
      int r2 = q[2];
      int c2 = q[3];
      int first;
      int next;

      int nc = c1;
      int nr = r1;

      int min = map[nr][nc];

      while (true) {
        first = map[nr][nc];

        if (nc == c2 && nr != r2) {
          nr++;
        } else if (nr == r2 && nc != c1) {
          nc--;
        } else if (nc == c1) {
          nr--;
        } else {
          nc++;
        }

        next = map[nr][nc];
        map[nr][nc] = first;
        min = Math.min(min, next);

        if (nr == r1 && nc == c1) {
          break;
        }
      }

      ans.add(min);
    }

    int[] a = new int[ans.size()];

    for (int i = 0; i < ans.size(); i++) {
      a[i] = ans.get(i);
    }

    return a;
  }
}
