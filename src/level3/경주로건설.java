package level3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 경주로건설 {

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        System.out.println(solution(board));
    }

    public static int solution(int[][] board) {
        int r = board.length;
        int c = board[0].length;

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        int[][][] costs = new int[r][c][2];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                Arrays.fill(costs[i][j], Integer.MAX_VALUE);
            }
        }
        costs[0][0][0] = 0;
        costs[0][0][1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        pq.add(new Node(0, 0, 0, 0));
        pq.add(new Node(0, 0, 1, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.cost > costs[now.r][now.c][now.direction]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                int nd = i / 2;
                int nCost = now.direction == nd ? now.cost + 100 : now.cost + 600;

                if (nr < 0 || nr >= r || nc < 0 || nc >= c) {
                    continue;
                }
                if (board[nr][nc] == 1) {
                    continue;
                }
                if (nCost >= costs[nr][nc][nd]) {
                    continue;
                }

                costs[nr][nc][nd] = nCost;
                pq.offer(new Node(nr, nc, nd, nCost));
            }
        }

        return Math.min(costs[r - 1][c - 1][0], costs[r - 1][c - 1][1]);
    }

    public static class Node {

        int r, c, direction, cost;

        public Node(int r, int c, int direction, int cost) {
            this.r = r;
            this.c = c;
            this.direction = direction;
            this.cost = cost;
        }
    }

}
