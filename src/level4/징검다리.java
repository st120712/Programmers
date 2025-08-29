package level4;

import java.util.Arrays;

public class 징검다리 {

    public static void main(String[] args) {
        int distance = 25;
        int[] rocks = {2, 14, 11, 21, 17};
        int n = 2;

        System.out.println(solution(distance, rocks, n));
    }

    public static int solution(int distance, int[] rocks, int n) {
        int left = 0;
        int right = distance;
        int mid = (left + right) / 2;

        Arrays.sort(rocks);
        int[] nRocks = new int[rocks.length + 2];
        nRocks[0] = 0;
        nRocks[nRocks.length - 1] = distance;
        for (int i = 1; i < nRocks.length - 1; i++) {
            nRocks[i] = rocks[i - 1];
        }

        while (left <= right) {
            int prev = nRocks[0];
            int rmCnt = 0;

            for (int i = 1; i < nRocks.length; i++) {
                if (nRocks[i] - prev < mid) {
                    rmCnt++;
                } else {
                    prev = nRocks[i];
                }

                if (rmCnt > n) {
                    break;
                }
            }

            if (rmCnt <= n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

            mid = (left + right) / 2;
        }

        return right;
    }
}
