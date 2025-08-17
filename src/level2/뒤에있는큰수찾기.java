package level2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class 뒤에있는큰수찾기 {

    public static void main(String[] args) {
        int[] numbers = {9, 1, 5, 3, 6, 2};

        System.out.println(Arrays.toString(solution(numbers)));
    }

    public static int[] solution(int[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        Arrays.fill(answer, -1);

        Deque<Integer> st = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && numbers[st.peek()] < numbers[i]) {
                answer[st.pop()] = numbers[i];
            }
            st.push(i);
        }

        return answer;
    }
}
