package level2;

import java.util.Stack;

public class 괄호회전하기 {

    public static void main(String[] args) {
        String s = "[)(]";
        System.out.println(solution(s));
    }

    public static int solution(String s) {
        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            sb.append(ch);
        }

        int ans = 0;

        if (s.length() % 2 == 1) {
            return 0;
        }

        for (int i = 0; i < s.length(); i++) {
            if (isCorrect(sb.toString())) {
                ans++;
            }
            sb.append(sb.charAt(0));
            sb.deleteCharAt(0);
        }

        return ans;
    }

    public static boolean isCorrect(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(ch);
            } else {
                char ch1 = stack.peek();

                if ((ch1 == '[' && ch == ']')
                        || (ch1 == '{' && ch == '}')
                        || (ch1 == '(' && ch == ')')) {
                    stack.pop();
                } else {
                    stack.push(ch);
                }
            }
        }

        return stack.isEmpty();
    }
}
