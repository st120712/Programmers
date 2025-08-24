package level3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 디스크컨트롤러 {
  public static void main(String[] args) {
    int[][] jobs = { { 0, 3 }, { 1, 9 }, { 3, 5 } };
    System.out.println(solution(jobs));
  }

  public static int solution(int[][] jobs) {
    int n = jobs.length;

    int[][] newJobs = new int[n][3];
    for (int i = 0; i < n; i++) {
      newJobs[i] = new int[] { i, jobs[i][0], jobs[i][1] };
    }
    Arrays.sort(newJobs, Comparator.comparingInt(a -> a[1]));

    int time = 0;
    int idx = 0;

    PriorityQueue<int[]> wait = new PriorityQueue<>((a, b) -> {
      if (a[2] != b[2]) {
        return a[2] - b[2];
      } else if (a[1] != b[1]) {
        return a[1] - b[1];
      }
      return a[0] - b[0];
    });

    int ans = 0;

    while (idx < n || !wait.isEmpty()) {
      while (idx < n && newJobs[idx][1] <= time) {
        wait.offer(newJobs[idx++]);
      }

      if (wait.isEmpty()) {
        time = newJobs[idx][1];
        continue;
      }

      int[] curProcess = wait.poll();
      time += curProcess[2];
      ans += time - curProcess[1];
    }

    return ans / jobs.length;
  }
}
