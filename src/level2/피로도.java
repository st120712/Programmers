package level2;

public class 피로도 {
  public static void main(String[] args) {
    int[][] dungeons = { { 80, 20 }, { 50, 40 }, { 30, 10 } };
    int k = 80;

    System.out.println(solution(k, dungeons));
  }

  public static int solution(int k, int[][] dungeons) {

    int cnt = 0;

    boolean[] visited = new boolean[dungeons.length];
    for (int i = 0; i < dungeons.length; i++) {
      visited[i] = true;
      cnt = Math.max(cnt, dfs(k, dungeons[i], 0, dungeons));
    }
    return 0;
  }

  public static int dfs(int k, int[] dungeon, boolean[] visited, int cnt, int[][] dungeons) {
    if (k < dungeon[0]) {
      return cnt;
    }

    cnt++;
    for (int : dungeons) {
      return dfs(k - dungeon[1], d, cnt, dungeons);
    }
  }
}
