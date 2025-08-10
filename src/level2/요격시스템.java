package level2;

import java.util.Arrays;

public class 요격시스템 {

    public static void main(String[] args) {
        int[][] targets = {{4, 5}, {4, 8}, {10, 14}, {11, 13}, {5, 12}, {3, 7}, {1, 4}};

        System.out.println(solution(targets));
    }

    public static int solution(int[][] targets) {
        int answer = 0;
        int last = 0;

        // 객체 배열(int[][]) → TimSort 사용
        Arrays.sort(targets, (a, b) -> {
            if (a[1] == b[1]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        for (int i = 0; i < targets.length; i++) {
            if (targets[i][0] >= last) {
                answer++;
                last = targets[i][1];
            }
        }

        return answer;
    }
}
