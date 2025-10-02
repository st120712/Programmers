package level3;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class 서틀버스 {

    public static void main(String[] args) {
        int n = 10, t = 60, m = 45;
        String[] timetable = {"23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"};
        System.out.println(solution(n, t, m, timetable));
    }

    static String solution(int n, int t, int m, String[] timetable) {
        int[] times = toIntTable(timetable);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int time : times) {
            pq.offer(time);
        }

        List<Integer>[] bus = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            bus[i] = list;

            int busTime = 540 + i * t;

            while (list.size() < m && !pq.isEmpty()) {
                int time = pq.peek();

                if (busTime >= time) {
                    list.add(pq.poll());
                } else {
                    break;
                }
            }
        }

        int ans;

        if (bus[bus.length - 1].size() < m) {
            ans = 540 + (n - 1) * t;
        } else {
            ans = bus[bus.length - 1].get(m - 1) - 1;
        }

        return toHHMM(ans);
    }

    static int[] toIntTable(String[] table) {
        int n = table.length;
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = toMinutes(table[i]);
        }

        return arr;
    }

    static int toMinutes(String time) {
        String[] arr = time.split(":");

        return Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
    }

    static String toHHMM(int minutes) {
        int h = minutes / 60;
        int m = minutes % 60;

        String HH = String.valueOf(h).length() == 1 ? "0" + h : h + "";
        String MM = String.valueOf(m).length() == 1 ? "0" + m : m + "";

        return HH + ":" + MM;
    }
}
