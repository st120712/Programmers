package level2;

public class 두원사이의정수쌍 {

    public static void main(String[] args) {
        System.out.println(solution(2, 3));
    }

    public static long solution(int r1, int r2) {
        long answer = countInclusive(r2) - countInclusive(r1) + boundaryPoints(r1);

        return answer;
    }

    public static long countInclusive(int r) {
        long r2 = 1L * r * r;
        long cnt = 1 + 4L * r; // (0,0) + 축 위의 점들

        for (long i = 1; i < r; i++) {
            long y = floorSqrt(r2 - i * i);
            cnt += 4 * y;
        }

        return cnt;
    }

    public static long floorSqrt(long n) {
        if (n <= 0) {
            return 0;
        }
        long r = (long) Math.sqrt(n);

        while ((r + 1) * (r + 1) <= n) {
            r++;
        }
        while (r * r > n) {
            r--;
        }
        return r;
    }

    public static long boundaryPoints(int r) {
        long r2 = 1L * r * r;
        long cnt = 0;

        for (long x = 0; x <= r; x++) {
            long y2 = r2 - x * x;
            long y = floorSqrt(y2);
            if (y * y == y2) {
                if (x == 0 || y == 0) {
                    cnt += 2;
                } else {
                    cnt += 4;
                }
            }
        }

        return cnt;
    }
}
