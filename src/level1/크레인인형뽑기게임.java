package level1;

import java.util.Stack;

public class 크레인인형뽑기게임 {

    public static void main(String[] args) {

    }

    static int solution(int[][] board, int[] moves) {
        Stack<Integer> stack = new Stack<>();

        int removed = 0;
        for (int m : moves) {
            int c = m - 1;
            for (int r = 0; r < board.length; r++) {
                if (board[r][c] != 0) {
                    boolean same = false;
                    if (!stack.isEmpty()) {
                        int p = stack.peek();
                        if (p == board[r][c]) {
                            same = true;
                        }
                    }
                    if (same) {
                        stack.pop();
                        removed += 2;
                    } else {
                        stack.push(board[r][c]);
                    }

                    board[r][c] = 0;
                    break;
                }
            }
        }

        return removed;
    }
}
