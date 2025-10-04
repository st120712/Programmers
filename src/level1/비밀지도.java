package level1;

import java.util.Arrays;
import java.util.Stack;

public class 비밀지도 {
    // 1. 지도 : n x n 정사각형
    // 2. 공백(" ") 또는 "#"으로 이루어짐
    // 3. 지도1 + 지도2 = 전체 지도
    // 4. 지도1 벽 || 지도2 벽 == 전체 지도 벽
    // 4-1. 지도1 공백 && 지도2 공백 == 전체 지도 공백
    // 5. 지도1 과 2는 정수배열로 암호화 됌
    // 6. 각 행을 벽 1, 공백 0으로 부호화한 이진수의 십진수 형태로 암호화

    public static void main(String[] args) {
        int n = 5;
        int[] arr1 = {9, 20, 28, 18, 11};
        int[] arr2 = {30, 1, 21, 17, 28};
        System.out.println(Arrays.toString(solution(n, arr1, arr2)));
    }

    static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] part1 = new String[n];
        String[] part2 = new String[n];

        for (int i = 0; i < n; i++) {
            part1[i] = toBinaryString(n, arr1[i]);
            part2[i] = toBinaryString(n, arr2[i]);
        }

        String[] ans = new String[n];

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder(n);
            for (int j = 0; j < n; j++) {
                if (part1[i].charAt(j) == '0' && part2[i].charAt(j) == '0') {
                    sb.append(" ");
                } else {
                    sb.append('#');
                }
            }
            ans[i] = sb.toString();
        }

        return ans;
    }

    static String toBinaryString(int n, int number) {
        Stack<Integer> stack = new Stack<>();
        while (number > 0) {
            stack.push(number % 2);
            number /= 2;
        }

        StringBuilder sb = new StringBuilder(n);
        int pad = n - stack.size();
        for (int i = 0; i < pad; i++) {
            sb.append('0');
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
