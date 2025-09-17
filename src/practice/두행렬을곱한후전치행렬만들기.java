package practice;

import java.util.Arrays;

public class 두행렬을곱한후전치행렬만들기 {

    public static void main(String[] args) {
        int[][] matrix1 = {{2, 4, 6}, {1, 3, 5}, {7, 8, 9}};
        int[][] matrix2 = {{9, 1, 2}, {4, 5, 6}, {7, 3, 8}};
        System.out.println(Arrays.deepToString(solution(matrix1, matrix2)));
    }

    public static int[][] solution(int[][] matrix1, int[][] matrix2) {
        int r1 = matrix1.length;
        int c1 = matrix1[0].length;
        int r2 = matrix2.length;
        int c2 = matrix2[0].length;

        int[][] matrix3 = new int[c2][r1];

        for (int i = 0; i < r1; i++) {
            for (int k = 0; k < r2; k++) {
                for (int j = 0; j < c2; j++) {
                    matrix3[i][k] += matrix1[i][j] * matrix2[j][k];
                }
            }
        }

        int[][] ans = new int[c2][r1];

        for (int i = 0; i < c2; i++) {
            for (int j = 0; j < r1; j++) {
                ans[i][j] = matrix3[j][i];
            }
        }

        for (int[] a : ans) {
            System.out.println(Arrays.toString(a));
        }

        return ans;
    }
}
