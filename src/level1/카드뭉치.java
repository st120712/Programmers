package level1;

import java.util.ArrayDeque;
import java.util.Queue;

public class 카드뭉치 {

    public static void main(String[] args) {

    }

    public static String solution(String[] card1, String[] card2, String[] goal) {
        Queue<String> c1 = new ArrayDeque<>();
        Queue<String> c2 = new ArrayDeque<>();
        Queue<String> g = new ArrayDeque<>();

        for (String s : card1) {
            c1.offer(s);
        }
        for (String s : card2) {
            c2.offer(s);
        }
        for (String s : goal) {
            g.offer(s);
        }

        while (!g.isEmpty()) {
            if (!c1.isEmpty() && c1.peek().equals(g.peek())) {
                c1.poll();
                g.poll();
            } else if (!c2.isEmpty() && c2.peek().equals(g.peek())) {
                c2.poll();
                g.poll();
            } else {
                return "No";
            }
        }

        return "Yes";
    }
}
