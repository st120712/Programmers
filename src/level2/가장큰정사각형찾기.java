package level2;

public class 가장큰정사각형찾기 {

    public static void main(String[] args) {

    }

    public static int solution(int[][] board) {
        int r = board.length;
        int c = board[0].length;

        int ans = 0;

        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (board[i][j] == 1) {
                    board[i][j] += Math.min(Math.min(board[i - 1][j - 1], board[i][j - 1]), board[i - 1][j]);
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                ans = Math.max(board[i][j], ans);
            }
        }

        return ans * ans;
    }
}
