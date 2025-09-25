package test.test4;

import java.util.ArrayDeque;

public class Exam2 {

    public static void main(String[] args) {
        int[] queue1 = {3, 2, 7, 2};
        int[] queue2 = {4, 6, 5, 1};
        System.out.println(solution(queue1, queue2));
    }

    static int solution(int[] queue1, int[] queue2) {
        long sum1 = 0;
        long sum2 = 0;
        ArrayDeque<Integer> q1 = new ArrayDeque<>();
        ArrayDeque<Integer> q2 = new ArrayDeque<>();
        for (int i = 0; i < queue1.length; i++) {
            sum1 += queue1[i];
            q1.offer(queue1[i]);
        }
        for (int i = 0; i < queue2.length; i++) {
            sum2 += queue2[i];
            q2.offer(queue2[i]);
        }

        int n = queue1.length + queue2.length;
        for (int i = 0; i < 2 * n; i++) {
            if (sum1 == sum2) {
                return i;
            }

            if (sum1 > sum2) {
                int number = q1.poll();
                sum1 -= number;
                sum2 += number;
                q2.offer(number);
            } else {
                int number = q2.poll();
                sum2 -= number;
                sum1 += number;
                q1.offer(number);
            }
        }

        return -1;
    }
}
