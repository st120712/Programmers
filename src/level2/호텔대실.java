package level2;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class 호텔대실 {

    public static void main(String[] args) {
        String[][] bookTime = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};

        System.out.println(solution(bookTime));
    }

    public static int solution(String[][] bookTime) {
        int n = bookTime.length;
        int[][] bookings = new int[n][2];

        for (int i = 0; i < n; i++) {
            int colon = 2;
            int start = Integer.parseInt(bookTime[i][0].substring(0, colon)) * 60 + Integer.parseInt(bookTime[i][0].substring(colon + 1));
            int end = Integer.parseInt(bookTime[i][1].substring(0, colon)) * 60 + Integer.parseInt(bookTime[i][1].substring(colon + 1));

            bookings[i] = new int[]{start, end};
        }
        Arrays.sort(bookings, (a, b) -> a[0] - b[0]);

        Queue<Integer> roomAvail = new PriorityQueue<>();

        int maxRooms = 0;
        for (int i = 0; i < n; i++) {
            int start = bookings[i][0];
            int endWithClean = bookings[i][1] + 10;

            if (!roomAvail.isEmpty() && roomAvail.peek() <= start) {
                roomAvail.poll();
            }

            roomAvail.offer(endWithClean);

            if (roomAvail.size() > maxRooms) {
                maxRooms = roomAvail.size();
            }
        }

        return maxRooms;
    }
}
