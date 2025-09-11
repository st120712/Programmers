package level1;

import java.util.Arrays;

public class 문자열내마음대로정렬하기 {

    public static void main(String[] args) {
        int n = 2;
        String[] strings = {"abce", "abcd", "cdx"};
        System.out.println(Arrays.toString(solution(strings, n)));
    }

    public static String[] solution(String[] strings, int n) {
        Arrays.sort(strings, (a, b) -> {
            if (a.charAt(n) == b.charAt(n)) {
                return a.compareTo(b);
            }
            return Character.compare(a.charAt(n), b.charAt(n));
        });

        return strings;
    }
}
