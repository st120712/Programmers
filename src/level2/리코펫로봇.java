package level2;

import java.util.LinkedList;
import java.util.Queue;

public class 리코펫로봇 {

    public static void main(String[] args) {
        String[] board = {".D.R", "....", ".G..", "...D"};

        System.out.println(solution(board));
    }

    public static int solution(String[] board) {
        int r = board.length;
        int c = board[0].length();

        // 빈 공간 0, 장애물 1, 목표 지점 2
        int[][] intBoard = new int[r][c];
        int[] start = new int[2];
        setIntBoard(board, r, c, intBoard, start);

        // bfs 알고리즘 사용
        Queue<int[]> posQ = new LinkedList<>();
        posQ.offer(start);

        // 각 칸에 도착했을 때 현재 카운터를 저장
        int[][] count = new int[r][c];
        boolean[][] visited = new boolean[r][c];
        visited[start[0]][start[1]] = true;

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!posQ.isEmpty()) {
            int[] curPos = posQ.poll();

            int cr = curPos[0];
            int cc = curPos[1];

            if (intBoard[cr][cc] == 2) {
                return count[cr][cc];
            }

            for (int[] d : directions) {
                int nr = cr;
                int nc = cc;

                while (true) {
                    int tr = nr + d[0];
                    int tc = nc + d[1];

                    if (tr < 0 || tr >= r || tc < 0 || tc >= c || intBoard[tr][tc] == 1) {
                        break;
                    }

                    nr = tr;
                    nc = tc;
                }

                if (visited[nr][nc]) {
                    continue;
                }

                visited[nr][nc] = true;
                count[nr][nc] = count[cr][cc] + 1;
                posQ.add(new int[]{nr, nc});
            }
        }

        return -1;
    }

    public static void setIntBoard(String[] board, int r, int c, int[][] intBoard, int[] start) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int input = 0;
                if (board[i].charAt(j) == '.') {
                    input = 0;
                }
                if (board[i].charAt(j) == 'D') {
                    input = 1;
                }
                if (board[i].charAt(j) == 'G') {
                    input = 2;
                }
                if (board[i].charAt(j) == 'R') {
                    input = 0;
                    start[0] = i;
                    start[1] = j;
                }
                intBoard[i][j] = input;
            }
        }
    }
}
