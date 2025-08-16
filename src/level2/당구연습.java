package level2;

import java.util.Arrays;

public class 당구연습 {

    public static void main(String[] args) {
        int m = 10;
        int n = 10;
        int startX = 3;
        int startY = 7;
        int[][] balls = {{7, 7}, {2, 7}, {7, 3}};

        System.out.println(Arrays.toString(solution(m, n, startX, startY, balls)));
    }

    public static int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        int size = 0;
        int[][] symmetryStarts = {{startX, 2 * n - startY}, {startX, -startY}, {-startX, startY}, {2 * m - startX, startY}};

        for (int[] ball : balls) {
            int endX = ball[0];
            int endY = ball[1];

            int minDistance = Integer.MAX_VALUE;

            for (int i = 0; i < symmetryStarts.length; i++) {
                int sx = symmetryStarts[i][0];
                int sy = symmetryStarts[i][1];

                if (i == 0 && startX == endX && startY < endY) {
                    continue;
                }
                if (i == 1 && startX == endX && startY > endY) {
                    continue;
                }
                if (i == 2 && startY == endY && startX > endX) {
                    continue;
                }
                if (i == 3 && startY == endY && startX < endX) {
                    continue;
                }

                minDistance = (int) Math.min(minDistance, Math.pow(sx - endX, 2) + Math.pow(sy - endY, 2));
            }

            answer[size++] = minDistance;
        }

        return answer;
    }
}
