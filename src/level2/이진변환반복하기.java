package level2;

import java.util.Arrays;
import java.util.Stack;

public class 이진변환반복하기 {

    public static void main(String[] args) {
        String s = "110010101001";
        System.out.println(Arrays.toString(solution(s)));
    }

    public static int[] solution(String s) {
        int cnt = 0;
        int remove = 0;

        while (!s.equals("1")) {
            cnt++;
            int len = s.length();
            for (char c : s.toCharArray()) {
                if (c == '0') {
                    len--;
                    remove++;
                }
            }

            s = toBinary(len);
        }

        return new int[]{cnt, remove};
    }

    static String toBinary(int n) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        while (n != 1) {
            stack.push(n % 2);
            n /= 2;
        }
        stack.push(n);

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }
}
