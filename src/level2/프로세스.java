package level2;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class 프로세스 {
  public static void main(String[] args) {

  }

  public static int solution(int[] priorities, int location) {
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
    Queue<Integer> q = new ArrayDeque<>();
    for (int n : priorities) {
      pq.offer(n);
      q.offer(n);
    }

    int ans = 0;
    while (!pq.isEmpty()) {
      int nextJob = pq.poll();

      while (true) {
        int cur = q.poll();
        if (nextJob == cur) {
          ans++;
          if (location == 0) {
            return ans;
          }
          location--;
          break;
        } else {
          if (location == 0) {
            location = q.size();
          } else {
            location--;
          }
          q.offer(cur);
        }
      }
    }

    return -1;
  }
}
