package practice;

import java.util.Arrays;
import java.util.Collections;

public class 계수정렬구현 {

    public static void main(String[] args) {
        long n = 118372;
        System.out.println(solution(n));
    }

    public static long solution(long n) {
        String[] digit = ("" + n).split("");
        Arrays.sort(digit, Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        Arrays.stream(digit).forEach(sb::append);
        return Long.parseLong(sb.toString());
    }
}
