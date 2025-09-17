package practice;

import java.util.Arrays;

public class 배열회전하기 {

    public static void main(String[] args) {
        int n = 2;
        int[][] arr = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        System.out.println(Arrays.deepToString(solution(arr, n)));
    }

    public static int[][] solution(int[][] arr, int n) {
        int r = arr.length;
        int c = arr[0].length;

        // 0,0 -> 0,c-1 / 0,1 -? 1,c-1 / ... / 2,0 -> 0,c-2
        for (int k = 0; k < n; k++) {
            int[][] ans = new int[r][c];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    ans[j][c - 1 - i] = arr[i][j];
                }
            }
            arr = ans;
        }

        return arr;
    }
}
