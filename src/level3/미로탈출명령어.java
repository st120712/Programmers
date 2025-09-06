package level3;

public class 미로탈출명령어 {

    public static void main(String[] args) {
        int n = 3, m = 4, x = 2, y = 3, r = 3, c = 1, k = 5;
        System.out.println(solution(n, m, x, y, r, c, k));
    }

    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        int dist = Math.abs(r - x) + Math.abs(c - y);
        if (dist > k) {
            return "impossible";
        }
        if ((k - dist) % 2 == 1) {
            return "impossible";
        }

        int[] dx = {1, 0, 0, -1};
        int[] dy = {0, -1, 1, 0};
        String[] dc = {"d", "l", "r", "u"};

        int cx = x, cy = y;
        int rem = k;
        StringBuilder sb = new StringBuilder(k);

        while (rem > 0) {
            boolean moved = false;
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 1 || nx > n || ny < 1 || ny > m) {
                    continue;
                }

                int need = Math.abs(r - nx) + Math.abs(c - ny);
                int left = rem - 1;
                if (need <= left && ((left - need) & 1) == 0) {
                    sb.append(dc[i]);
                    cx = nx;
                    cy = ny;
                    rem = left;
                    moved = true;
                    break;
                }
            }
            if (!moved) {
                return "impossible";
            }
        }
        return sb.toString();
    }
}
