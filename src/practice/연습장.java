package practice;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 연습장 {

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        System.out.println(solution(board));
    }

    public static int solution(int[][] board) {
        int n = board.length;
        int[][][] costs = new int[2][n][n];
        for (int[][] arr : costs) {
            for (int[] a : arr) {
                Arrays.fill(a, Integer.MAX_VALUE);
            }
        }
        costs[0][0][0] = 0;
        costs[1][0][0] = 0;

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
        pq.add(new Node(0, 0, 0, 0));
        pq.add(new Node(0, 0, 0, 1));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.cost > costs[cur.direction][cur.r][cur.c]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                int nd = i / 2;
                int nCost = cur.direction == nd ? cur.cost + 100 : cur.cost + 600;

                if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                    continue;
                }
                if (board[nr][nc] == 1) {
                    continue;
                }
                if (nCost >= costs[nd][nr][nc]) {
                    continue;
                }

                costs[nd][nr][nc] = nCost;
                pq.offer(new Node(nr, nc, nCost, nd));
            }
        }

        return Math.min(costs[0][n - 1][n - 1], costs[1][n - 1][n - 1]);
    }

    static class Node {

        int r;
        int c;
        int cost;
        int direction;

        public Node(int r, int c, int cost, int direction) {
            this.r = r;
            this.c = c;
            this.cost = cost;
            this.direction = direction;
        }
    }
}
