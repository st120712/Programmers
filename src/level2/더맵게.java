package level2;

import java.util.PriorityQueue;

public class 더맵게 {
  public static void main(String[] args) {

  }

  public static int solution(int[] scoville, int K) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int s : scoville) {
      pq.offer(s);
    }

    int cnt = 0;
    while (pq.size() > 1 && pq.peek() < K) {
      int a = pq.poll();
      int b = pq.poll();

      pq.offer(a + (b * 2));
      cnt++;
    }

    if (pq.peek() < K) {
      return -1;
    }

    return cnt;
  }
}
