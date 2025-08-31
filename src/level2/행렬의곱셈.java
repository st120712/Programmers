package level2;

public class 행렬의곱셈 {

    public static void main(String[] args) {
        int[][] arr1 = {{1, 4}, {3, 2}, {4, 1}};
        int[][] arr2 = {{3, 3}, {3, 3}};

        System.out.println(solution(arr1, arr2));

    }

    public static int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] ans = new int[arr1.length][arr2[0].length];

        for (int k = 0; k < arr1.length; k++) {
            for (int j = 0; j < arr2[0].length; j++) {
                for (int i = 0; i < arr1[0].length; i++) {
                    ans[k][j] += arr1[k][i] * arr2[i][j];
                }
            }
        }

        return ans;
    }
}
