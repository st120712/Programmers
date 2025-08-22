package level2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class 구명보트 {

    public static void main(String[] args) {
        int[] people = {30, 30, 30, 70, 90, 90};
        int limit = 100;

        System.out.println(solution(people, limit));
    }

    public static int solution(int[] people, int limit) {
        Arrays.sort(people);
        Deque<Integer> q = new ArrayDeque<>();
        for (int p : people) {
            q.offer(p);
        }

        int ans = 0;

        while (!q.isEmpty()) {
            int first = q.poll();
            int remain = limit - first;

            while (true) {
                int last = q.pollLast();

                if (remain - last < 0) {
                    ans++;
                } else {
                    remain -= last;

                    if (q.isEmpty()) {
                        break;
                    }

                    if (remain - q.peek() < 0) {
                        ans++;
                        break;
                    }
                }
            }
        }

        return ans;
    }
}
