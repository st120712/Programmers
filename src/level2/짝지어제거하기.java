package level2;

import java.util.ArrayDeque;
import java.util.Deque;

public class 짝지어제거하기 {

    public static void main(String[] args) {
        String s = "cdcd";
        System.out.println(solution(s));
    }

    public static int solution(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == ch) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }

        if (stack.isEmpty()) {
            return 1;
        }
        return 0;
    }
}
