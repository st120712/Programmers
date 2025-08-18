package level2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class 숫자변환하기 {

    public static void main(String[] args) {
        int x = 2;
        int y = 100;
        int n = 4;

        System.out.println(solution(x, y, n));
    }

    public static int solution(int x, int y, int n) {
        if (x == y) {
            return 0;
        }

        int[] dist = new int[y + 1];
        Arrays.fill(dist, -1);

        Deque<Integer> q = new ArrayDeque<>();
        q.offer(x);
        dist[x] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            int[] nextNumbers = {cur + n, cur * 2, cur * 3};

            for (int nextNumber : nextNumbers) {
                if (nextNumber <= y && dist[nextNumber] == -1) {
                    dist[nextNumber] = dist[cur] + 1;
                    q.offer(nextNumber);
                }
            }
        }

        return dist[y];
    }
}
