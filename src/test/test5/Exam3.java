package test.test5;

import java.util.Arrays;

public class Exam3 {

    public static void main(String[] args) {
        int[] lottos = {44, 1, 0, 0, 31, 25};
        int[] winNums = {31, 10, 45, 1, 6, 19};
        System.out.println(Arrays.toString(solution(lottos, winNums)));
    }

    static int[] solution(int[] lottos, int[] winNums) {
        int[] rank = {6, 6, 5, 4, 3, 2, 1};

        boolean[] win = new boolean[46];
        for (int w : winNums) {
            win[w] = true;
        }

        int cnt = 0;
        int zero = 0;
        for (int l : lottos) {
            if (l == 0) {
                zero++;
            } else {
                if (win[l]) {
                    cnt++;
                }
            }
        }

        int bestCnt = cnt + zero;
        int worstCnt = cnt;

        return new int[]{rank[bestCnt], rank[worstCnt]};
    }
}
