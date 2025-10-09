package level2;

import java.util.Stack;

public class 괄호변환 {

    public static void main(String[] args) {
        String p = "()))((()";
        System.out.println(solution(p));
    }

    static String solution(String p) {
        return convertRightString(p);
    }

    static String convertRightString(String p) {
        if (rightString(p)) {
            return p;
        }
        String[] strings = splitFirstBalancedString(p);
        String u = strings[0];
        String v = strings[1];

        StringBuilder sb = new StringBuilder();
        if (!rightString(u)) {
            sb.append('(');
            sb.append(convertRightString(v));
            sb.append(')');

            for (int i = 1; i < u.length() - 1; i++) {
                if (u.charAt(i) == '(') {
                    sb.append(')');
                } else {
                    sb.append('(');
                }
            }

        } else {
            sb.append(u);
            sb.append(convertRightString(v));
        }

        return sb.toString();
    }

    static String[] splitFirstBalancedString(String p) {
        int[] cnt = new int[2];
        String[] arr = new String[]{"", ""};

        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                cnt[0]++;
            } else {
                cnt[1]++;
            }

            if (cnt[0] == cnt[1]) {
                arr[0] = p.substring(0, i + 1);
                arr[1] = p.substring(i + 1, p.length());
                break;
            }
        }

        return arr;
    }

    static boolean rightString(String p) {
        Stack<Character> stack = new Stack<>();

        for (char c : p.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                if (stack.peek() == '(' && c == ')') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }

        return stack.isEmpty();
    }
}
