package test.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exam2 {

    public static void main(String[] args) {
        int[][] arr = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        System.out.println(Arrays.toString(solution(arr)));
    }

    static int[] ans = new int[2];

    static int[] solution(int[][] arr) {
        int n = arr.length;

        int first = arr[0][0];
        boolean isSame = true;
        for (int[] a : arr) {
            for (int i : a) {
                if (first != i) {
                    isSame = false;
                    break;
                }
            }
            if (!isSame) {
                break;
            }
        }

        if (isSame) {
            ans[first]++;
            return ans;
        }

        dfs(arr, n);

        return ans;
    }

    static void dfs(int[][] arr, int n) {
        if (n == 1) {
            ans[arr[0][0]]++;
            return;
        }
        n /= 2;

        List<int[][]> list = new ArrayList<>();
        int[][][] arrs = new int[4][][];

        int[][] temp1 = new int[n][];
        int[][] temp2 = new int[n][];
        int[][] temp3 = new int[n][];
        int[][] temp4 = new int[n][];
        for (int i = 0; i < n; i++) {
            temp1[i] = Arrays.copyOfRange(arr[i], 0, n);
            temp2[i] = Arrays.copyOfRange(arr[n + i], 0, n);
            temp3[i] = Arrays.copyOfRange(arr[i], n, n * 2);
            temp4[i] = Arrays.copyOfRange(arr[n + i], n, n * 2);
        }
        arrs[0] = temp1;
        arrs[1] = temp2;
        arrs[2] = temp3;
        arrs[3] = temp4;

        for (int i = 0; i < 4; i++) {
            int[][] temp = arrs[i];
            int first = temp[0][0];
            boolean able = true;
            for (int[] k : temp) {
                for (int j : k) {
                    if (first != j) {
                        able = false;
                        break;
                    }
                }
                if (!able) {
                    break;
                }
            }

            if (able) {
                ans[first]++;
            } else {
                list.add(arrs[i]);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            dfs(list.get(i), n);
        }
    }
}
