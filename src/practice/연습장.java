package practice;

import java.util.Arrays;

public class 연습장 {

    public static void main(String[] args) {
        int[] numbers = {6, 10, 2};
        System.out.println(solution(numbers));
    }

    public static String solution(int[] numbers) {
        String[] arr = Arrays.stream(numbers).mapToObj(String::valueOf).toArray(String[]::new);

        Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));

        if (arr[0].equals("0")) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        Arrays.stream(arr).forEach(sb::append);

        return sb.toString();
    }
}
