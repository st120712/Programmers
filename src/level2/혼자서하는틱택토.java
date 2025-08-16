package level2;

public class 혼자서하는틱택토 {

    public static void main(String[] args) {
        String[] board = {"OOO", "...", "XXX"};

        System.out.println(solution(board));
    }

    public static int solution(String[] board) {
        int numX = 0;
        int numO = 0;

        for (String line : board) {
            for (char c : line.toCharArray()) {
                if (c == 'X') {
                    numX++;
                }
                if (c == 'O') {
                    numO++;
                }
            }
        }

        if (numX > numO || numO - numX > 1) {
            return 0;
        }

        if (isWin(board, 'O') && isWin(board, 'X')) {
            return 0;
        }

        if (isWin(board, 'O') && numO - numX != 1) {
            return 0;
        }

        if (isWin(board, 'X') && numO - numX != 0) {
            return 0;
        }

        return 1;
    }

    public static boolean isWin(String[] board, char c) {
        if (c != 'O' && c != 'X') {
            throw new AssertionError();
        }

        int[][][] wins = {{{0, 0}, {0, 1}, {0, 2}}, {{1, 0}, {1, 1}, {1, 2}}, {{2, 0}, {2, 1}, {2, 2}}, {{0, 0}, {1, 0}, {2, 0}}, {{0, 1}, {1, 1}, {2, 1}}, {{0, 2}, {1, 2}, {2, 2}}, {{0, 0}, {1, 1}, {2, 2}}, {{2, 0}, {1, 1}, {0, 2}}};

        boolean[][] booleanBoard = new boolean[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == c) {
                    booleanBoard[i][j] = true;
                }
            }
        }

        for (int[][] w : wins) {
            int n = 0;

            for (int[] p : w) {
                if (!booleanBoard[p[0]][p[1]]) {
                    break;
                }
                n++;
            }

            if (n == 3) {
                return true;
            }
        }

        return false;
    }
}
