package level2;

import java.util.ArrayDeque;
import java.util.Queue;

public class 듀큐합같게만들기 {

    public static void main(String[] args) {
        int[] queue1 = {1, 2, 1, 2};
        int[] queue2 = {1, 10, 1, 2};

        System.out.println(solution(queue1, queue2));
    }

    public static int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        long q1Sum = 0;
        long q2Sum = 0;

        for (int n : queue1) {
            q1.offer(n);
            q1Sum += n;
        }
        for (int n : queue2) {
            q2.offer(n);
            q2Sum += n;
        }

        int q1Size = q1.size();
        int q2Size = q2.size();

        int count = 0;

        if ((q1Sum + q2Sum) % 2 != 0) {
            return -1;
        }

        while (count < (q1Size + q2Size) * 3) {
            if (q1Sum > q2Sum) {
                int n = q1.poll();
                q1Sum -= n;
                q2Sum += n;
                q2.offer(n);
                count++;
            } else if (q1Sum < q2Sum) {
                int n = q2.poll();
                q1Sum += n;
                q2Sum -= n;
                q1.offer(n);
                count++;
            } else {
                return count;
            }
        }

        return -1;
    }
}
