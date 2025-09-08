package practice;

import java.util.Arrays;

public class 연습장 {

    public static void main(String[] args) {
        int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        solution(board);
        for (int[] b : board) {
            System.out.println(Arrays.toString(b));
        };
    }

    static int[][] Board;

    public static int[][] solution(int[][] board) {
        Board = board;
        findSolution();
        return board;
    }

    static boolean findSolution() {
        Block emptyPos = findEmptyPosition();

        if (emptyPos == null) {
            return true;
        }

        int row = emptyPos.i;
        int col = emptyPos.j;

        for (int num = 1; num <= 9; num++) {
            if (isValid(num, row, col)) {
                Board[row][col] = num;
                if (findSolution()) {
                    return true;
                }
                Board[row][col] = 0;
            }
        }
        return false;
    }

    static Block findEmptyPosition() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Board[i][j] == 0) {
                    return new Block(i, j);
                }
            }
        }
        return null;
    }

    static boolean isValid(int num, int row, int col) {
        return !(inRow(num, row) || inCol(num, col) || inBox(num, row, col));
    }

    static boolean inRow(int num, int row) {
        return Arrays.stream(Board[row]).anyMatch(n -> n == num);
    }

    static boolean inCol(int num, int col) {
        for (int i = 0; i < 9; i++) {
            if (Board[i][col] == num) {
                return true;
            }
        }
        return false;
    }

    static boolean inBox(int num, int row, int col) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Board[(row / 3) * 3 + i][(col / 3) * 3 + j] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    static class Block {

        int i, j;

        public Block(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
