package level2;

import java.util.Arrays;

public class 카펫 {

    public static void main(String[] args) {
        int brown = 10;
        int yellow = 2;

        System.out.println(Arrays.toString(solution(brown, yellow)));
    }

    public static int[] solution(int brown, int yellow) {
        int r = 3;
        int c = 0;
        int s = brown + yellow;

        for (int i = r; i <= s; i++) {
            if (s % i == 0 && i <= s / i) {
                r = i;
                c = s / i;
                int numOfBrown = 2 * (r + c) - 4;

                if (numOfBrown == brown) {
                    return new int[]{c, r};
                }
            }
        }

        return new int[0];
    }
}
