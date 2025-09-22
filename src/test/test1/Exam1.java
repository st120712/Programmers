package test.test1;

public class Exam1 {

    public static void main(String[] args) {
        int n = 3, m = 4, x = 2, y = 3, r = 3, c = 1, k = 5;
        System.out.println(solution(n, m, x, y, r, c, k));
    }

    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        if (!validate(x, y, r, c, k)) {
            return "impossible";
        }

        //dlru
        int[] dx = {1, 0, 0, -1};
        int[] dy = {0, -1, 1, 0};

        StringBuilder sb = new StringBuilder();

        while (k != 0) {
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nk = k - 1;

                if (nx <= 0 || nx > n || ny <= 0 || ny > m) {
                    continue;
                }
                if (!validate(nx, ny, r, c, nk)) {
                    continue;
                }

                x = nx;
                y = ny;
                k = nk;
                if (i == 0) {
                    sb.append("d");
                }
                if (i == 1) {
                    sb.append("l");
                }
                if (i == 2) {
                    sb.append("r");
                }
                if (i == 3) {
                    sb.append("u");
                }
                break;
            }
        }

        return sb.toString();
    }

    static boolean validate(int x, int y, int r, int c, int k) {
        int remain = Math.abs(x - r) + Math.abs(y - c);
        if (remain > k) {
            return false;
        }
        if ((k - remain) % 2 != 0) {
            return false;
        }
        return true;
    }
}
