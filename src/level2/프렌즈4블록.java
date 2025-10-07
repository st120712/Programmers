package level2;

public class 프렌즈4블록 {

    // 1. (0, 0) 부터 (n-2, m-2) 까지 순회하며 그 블럭을 좌상으로 하는 2x2 정사각형임을 판단. 순회 완료 후 2x2로 판단된 블럭을 삭제
    // 2. 빈공간으로 남은 블럭이 떨어지고 1번 반복
    // 3. 사라진 블럭을 전체 카운팅
    public static void main(String[] args) {
        int m = 4, n = 5;
        String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
        System.out.println(solution(m, n, board));
    }

    static int solution(int m, int n, String[] board) {
        char[][] g = new char[m][n];
        for (int i = 0; i < m; i++) {
            g[i] = board[i].toCharArray();
        }

        int removedTotal = 0;

        while (true) {
            boolean[][] mark = new boolean[m][n];
            int removedThisRound = 0;

            for (int r = 0; r < m - 1; r++) {
                for (int c = 0; c < n - 1; c++) {
                    char ch = g[r][c];
                    if (ch == 0) {
                        continue;
                    }

                    if (g[r][c + 1] == ch && g[r + 1][c] == ch && g[r + 1][c + 1] == ch) {
                        mark[r][c] = mark[r][c + 1] = mark[r + 1][c] = mark[r + 1][c + 1] = true;
                    }
                }
            }

            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if (mark[r][c]) {
                        g[r][c] = 0;
                        removedThisRound++;
                    }
                }
            }
            if (removedThisRound == 0) {
                break;
            }
            removedTotal += removedThisRound;

            for (int c = 0; c < n; c++) {
                int write = m - 1;
                for (int r = m - 1; r >= 0; r--) {
                    if (g[r][c] != 0) {
                        g[write--][c] = g[r][c];
                    }
                }
                while (write >= 0) {
                    g[write--][c] = 0;
                }
            }
        }

        return removedTotal;
    }
}
