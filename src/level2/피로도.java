package level2;

import java.util.ArrayList;
import java.util.List;

public class 피로도 {
  public static void main(String[] args) {
    int[][] dungeons = { { 80, 20 }, { 50, 40 }, { 30, 10 } };
    int k = 80;

    System.out.println(solution(k, dungeons));
  }

  public static int solution(int k, int[][] dungeons) {
    boolean[] visited = new boolean[dungeons.length];
    List<Integer> countList = new ArrayList<>();

    dfs(k, dungeons, visited, 0, countList);

    int max = 0;
    for (int c : countList) {
      max = Math.max(max, c);
    }

    return max;
  }

  public static void dfs(int k, int[][] dungeons, boolean[] visited, int count, List<Integer> countList) {
    if (k < 0 || count == dungeons.length) {
      countList.add(count);
      return;
    }

    for (int i = 0; i < dungeons.length; i++) {
      if (visited[i] || dungeons[i][0] > k) {
        continue;
      }
      k -= dungeons[i][1];
      visited[i] = true;
      count++;
      dfs(k, dungeons, visited, count, countList);
      k += dungeons[i][1];
      visited[i] = false;
      count--;
    }
    countList.add(count);
  }
}
