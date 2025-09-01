package practice;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class 연습장 {

    public static void main(String[] args) {
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};
        System.out.println(Arrays.toString(solution(progresses, speeds)));
    }

    public static int[] solution(int[] progresses, int[] speeds) {
        int n = progresses.length;

        if (n == 1) {
            return new int[]{1};
        }

        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            q.offer((int) Math.ceil((100 - (double) progresses[i]) / speeds[i]));
        }

        List<Integer> ans = new ArrayList<>();

        while (!q.isEmpty()) {
            int cnt = 1;
            int p1 = q.poll();

            while (!q.isEmpty() && p1 >= q.peek()) {
                q.poll();
                cnt++;
            }
            ans.add(cnt);
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
