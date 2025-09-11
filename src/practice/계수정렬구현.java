package practice;

import java.util.Arrays;

public class 계수정렬구현 {

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5};
        int[] arr2 = {2, 4, 6};
        System.out.println(Arrays.toString(solution(arr1, arr2)));
    }

    public static int[] solution(int[] arr1, int[] arr2) {
        int p1 = 0;
        int p2 = 0;

        int[] ans = new int[arr1.length + arr2.length];

        for (int i = 0; i < ans.length; i++) {
            if (p1 == arr1.length) {
                ans[i] = arr2[p2++];
            } else if (p2 == arr1.length) {
                ans[i] = arr1[p1++];
            } else {
                if (arr1[p1] < arr2[p2]) {
                    ans[i] = arr1[p1++];
                } else {
                    ans[i] = arr2[p2++];
                }
            }
        }

        return ans;
    }
}
