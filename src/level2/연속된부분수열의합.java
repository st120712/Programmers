package level2;

import java.util.Arrays;

public class 연속된부분수열의합 {

    public static void main(String[] args) {
        int[] sequence = {1, 1, 1, 2, 3, 4, 5};
        int k = 5;

        System.out.println(Arrays.toString(solution(sequence, k)));
    }

    public static int[] solution(int[] sequence, int k) {
        int n = sequence.length;
        int left = 0;
        int sum = 0;
        int bestL = 0;
        int bestR = n;

        for (int right = 0; right < n; right++) {
            sum += sequence[right];

            while (sum >= k && left <= right) {
                if (sum == k) {
                    if ((right - left) < (bestR - bestL)) {
                        bestL = left;
                        bestR = right;
                    }
                }

                sum -= sequence[left++];
            }
        }

        return new int[]{bestL, bestR};
    }
}
