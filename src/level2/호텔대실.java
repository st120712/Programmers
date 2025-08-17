package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class 호텔대실 {

    public static void main(String[] args) {
        String[][] bookTime = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};

        System.out.println(solution(bookTime));
    }

    public static int solution(String[][] bookTime) {
        Queue<int[]> bookTimeInMiutes = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for (int i = 0; i < bookTime.length; i++) {
            int colon = 2;
            int start = Integer.parseInt(bookTime[i][0].substring(0, colon)) * 60 + Integer.parseInt(bookTime[i][0].substring(colon + 1));
            int end = Integer.parseInt(bookTime[i][1].substring(0, colon)) * 60 + Integer.parseInt(bookTime[i][1].substring(colon + 1));

            bookTimeInMiutes.offer(new int[]{start, end});
        }

        for (int[] i : bookTimeInMiutes) {
            System.out.println(Arrays.toString(i));
        }

        List<Integer> rooms = new ArrayList<>();
        rooms.add(bookTimeInMiutes.poll()[1] + 10);

        while (!bookTimeInMiutes.isEmpty()) {
            int[] book = bookTimeInMiutes.poll();
            int start = book[0];
            int end = book[1] + 10;

            int roomNum = -1;
            int interval = Integer.MAX_VALUE;
            for (int i = 0; i < rooms.size(); i++) {
                int newInterval = start - rooms.get(i);

                if (newInterval < 0) {
                    continue;
                }

                if (newInterval == 0) {
                    roomNum = i;
                    interval = newInterval;
                    break;
                }

                if (newInterval < interval) {
                    roomNum = i;
                    interval = newInterval;
                }
            }

            if (roomNum == -1) {
                rooms.add(end);
            } else {
                rooms.set(roomNum, end);
            }
        }

        return rooms.size();
    }
}
