package practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class 연습장 {

    public static void main(String[] args) {

    }

    public static int solution(String[][] bookTime) {
        int n = bookTime.length;
        int[][] bookings = new int[n][];

        for (int i = 0; i < n; i++) {
            int[] reserve = new int[2];
            reserve[0] = toMinutes(bookTime[i][0]);
            reserve[1] = toMinutes(bookTime[i][1]);
            bookings[i] = reserve;
        }

        Arrays.sort(bookings, Comparator.comparingInt(a -> a[0]));

        Queue<Integer> q = new PriorityQueue<>((a, b) -> a - b);
        q.offer(bookings[0][1] + 10);

        for (int i = 1; i < n; i++) {
            int nextStart = bookings[i][0];
            int nextEnd = bookings[i][1] + 10;

            if (q.peek() <= nextStart) {
                q.poll();
            }
            q.offer(nextEnd);
        }

        return q.size();
    }

    public static int toMinutes(String hhmm) {
        return Integer.parseInt(hhmm.split(":")[0]) * 60 + Integer.parseInt(hhmm.split(":")[1]);
    }
}
