package level2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

public class 과제진행하기 {

    public static void main(String[] args) {
        String[][] plans = {{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}};

        System.out.println(Arrays.toString(solution(plans)));
    }

    public static String[] solution(String[][] plans) {
        Arrays.sort(plans, Comparator.comparing(p -> p[1]));

        Deque<Plan> stack = new ArrayDeque<>();
        List<String> answer = new ArrayList<>();

        int curTime = toMinutes(plans[0][1]);

        for (String[] p : plans) {
            String task = p[0];
            int start = toMinutes(p[1]);
            int remain = Integer.parseInt(p[2]);

            while (!stack.isEmpty() && curTime + stack.peek().getRemain() <= start) {
                Plan top = stack.pop();
                curTime += top.getRemain();
                answer.add(top.getTask());
            }

            if (!stack.isEmpty() && curTime < start) {
                int available = start - curTime;
                stack.peek().setRemain(stack.peek().getRemain() - available);
                curTime = start;
            }

            if (curTime < start) {
                curTime = start;
            }

            stack.push(new Plan(task, start, remain));
        }

        while (!stack.isEmpty()) {
            answer.add(stack.pop().getTask());
        }

        return answer.toArray(new String[0]);
    }

    public static int toMinutes(String hhmm) {
        int colon = hhmm.indexOf(":");
        int h = Integer.parseInt(hhmm.substring(0, colon));
        int m = Integer.parseInt(hhmm.substring(colon + 1));

        return h * 60 + m;
    }

    public static class Plan {

        String task;
        int start;
        int remain;

        public Plan(String task, int start, int remain) {
            this.task = task;
            this.start = start;
            this.remain = remain;
        }

        public String getTask() {
            return task;
        }

        public int getStart() {
            return start;
        }

        public int getRemain() {
            return remain;
        }

        public void setRemain(int remain) {
            this.remain = remain;
        }

        @Override
        public String toString() {
            return String.format("task : %s time : %d remain : %d", task, start, remain);
        }

    }
}
