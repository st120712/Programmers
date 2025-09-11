package level3;

import java.util.HashMap;
import java.util.Map;

public class 사라지는발판 {

    public static void main(String[] args) {
        int[][] board = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int[] aloc = {1, 0};
        int[] bloc = {1, 2};
        System.out.println(solution(board, aloc, bloc));
    }

    static int R, C;
    static int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
    static Map<Long, Integer> memo = new HashMap<>();

    public static int solution(int[][] board, int[] aloc, int[] bloc) {
        R = board.length;
        C = board[0].length;
        int mask = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 1) {
                    mask |= 1 << (i * C + j);
                }
            }
        }

        int res = dfs(aloc[0], aloc[1], bloc[0], bloc[1], 0, mask);

        return Math.abs(res) - 1;
    }

    static int dfs(int ar, int ac, int br, int bc, int turn, int mask) {
        long k = key(ar, ac, br, bc, turn, mask, R * C);
        Integer cached = memo.get(k);
        if (cached != null) {
            return cached;
        }

        int r = (turn == 0 ? ar : br), c = (turn == 0 ? ac : bc);
        int bit = 1 << (r * C + c);
        if ((mask & bit) == 0) {
            memo.put(k, -1);
            return -1;
        }

        int winMin = Integer.MAX_VALUE;
        int loseMax = 0;
        boolean moved = false;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i], nc = c + dc[i];
            if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                continue;
            }
            int nbit = 1 << (nr * C + nc);
            if ((mask & nbit) == 0) {
                continue;
            }
            moved = true;

            int nmask = mask & ~bit;
            int child = (turn == 0)
                    ? dfs(nr, nc, br, bc, 1, nmask)
                    : dfs(ar, ac, nr, nc, 0, nmask);

            int v = Math.abs(child) + 1;
            if (child < 0) {
                winMin = Math.min(winMin, v);
            } else {
                loseMax = Math.max(loseMax, v);
            }
        }

        int res;
        if (!moved) {
            res = -1;
        } else if (winMin != Integer.MAX_VALUE) {
            res = +winMin;
        } else {
            res = -(loseMax);
        }
        memo.put(k, res);
        return res;
    }

    static long key(int ar, int ac, int br, int bc, int turn, int mask, int bits) {
        long k = mask & ((1L << bits) - 1);
        k = (k << 3) | ar;
        k = (k << 3) | ac;
        k = (k << 3) | br;
        k = (k << 3) | bc;
        k = (k << 1) | turn;
        return k;
    }
}
