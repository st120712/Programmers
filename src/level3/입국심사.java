package level3;

public class 입국심사 {

    public static void main(String[] args) {

    }

    public static long solution(int n, int[] times) {
        int min = Integer.MAX_VALUE;
        for (int time : times) {
            min = Math.min(min, time);
        }

        long right = n * (long) min;
        long left = 1;
        long t = (right + left) / 2;

        while (left < right) {
            long sum = 0;
            for (int time : times) {
                sum += t / time;
            }

            if (sum >= n) {
                right = t;
            } else if (sum < n) {
                left = t + 1;
            }

            t = (right + left) / 2;
        }

        return t;
    }
}
