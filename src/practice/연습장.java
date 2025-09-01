package practice;

import java.util.Stack;

public class 연습장 {

    public static void main(String[] args) {
        int decimal = 12345;
        System.out.println(solution(decimal));
    }

    public static String solution(int decimal) {
        Stack<Integer> stack = new Stack<>();

        while (decimal > 0) {
            int a = decimal / 2;
            int b = decimal % 2;

            if (a != 0) {
                stack.push(b);
            }
            if (a == 1) {
                stack.push(a);
            }
            decimal = a;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }
}
