package practice;

import java.util.Arrays;

public class 연습장 {

    public static void main(String[] args) {

    }

    public static int[] solution(int[] answers) {
        int n = answers.length;

        int[] s1 = {1, 2, 3, 4, 5};
        int[] s2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] s3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int a1 = 0;
        int a2 = 0;
        int a3 = 0;

        for (int i = 0; i < n; i++) {
            if (answers[i] == s1[i % s1.length]) {
                a1++;
            };

            if (answers[i] == s2[i % s2.length]) {
                a2++;
            }

            if (answers[i] == s3[i % s3.length]) {
                a3++;
            }
        }

        int maxA = Math.max(a1, a2);
        maxA = Math.max(maxA, a3);

        int[] ans = new int[3];
        int size = 0;

        if (maxA == a1) {
            ans[size++] = 1;
        }

        if (maxA == a2) {
            ans[size++] = 2;
        }

        if (maxA == a3) {
            ans[size++] = 3;
        }

        return Arrays.copyOf(ans, size);
    }
}
