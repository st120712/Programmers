package level4;

import java.util.PriorityQueue;

public class 지형이동 {

    public static void main(String[] args) {
        int[][] land = {{10, 11, 10, 11}, {2, 21, 20, 10}, {1, 20, 21, 11}, {2, 1, 2, 1}};
        int height = 1;
        System.out.println(solution(land, height));
    }

    static class Node {

        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Edge {

        Node node;
        int cost;

        public Edge(Node node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    public static int solution(int[][] land, int height) {
        int r = land.length;
        int c = land[0].length;
        boolean[][] visited = new boolean[r][c];
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.add(new Edge(new Node(0, 0), 0));

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        int ans = 0;
        int visitedCount = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int cHeight = land[cur.node.r][cur.node.c];

            if (visited[cur.node.r][cur.node.c]) {
                continue;
            }

            visited[cur.node.r][cur.node.c] = true;
            ans += cur.cost;
            visitedCount++;
            if (visitedCount == r * c) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.node.r + dr[i];
                int nc = cur.node.c + dc[i];

                if (nr < 0 || nr >= r || nc < 0 || nc >= c) {
                    continue;
                }
                if (visited[nr][nc]) {
                    continue;
                }

                int nHeight = land[nr][nc];
                int nCost = Math.abs(cHeight - nHeight) <= height ? 0 : Math.abs(cHeight - nHeight);
                Edge nEdge = new Edge(new Node(nr, nc), nCost);
                pq.offer(nEdge);
            }
        }

        return ans;
    }
}
