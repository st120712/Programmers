package level2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class 석유시추 {

    public static void main(String args[]) {
        int[][] land = {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}};

        System.out.println(solution(land));
    }

    public static int solution(int[][] land) {
        boolean[][] visited = new boolean[land.length][land[0].length];
        int[] columnOil = new int[land[0].length];
        int maxOil = 0;

        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                if (visited[i][j] || land[i][j] == 0) {
                    continue;
                }

                Petroleum p = new Petroleum();
                bfs(i, j, p, visited, land);
                for (int c : p.columns) {
                    columnOil[c] += p.amount;
                    if (maxOil < columnOil[c]) {
                        maxOil = columnOil[c];
                    }
                }
            }
        }

        return maxOil;
    }

    public static void bfs(int startR, int startC, Petroleum p, boolean[][] visited, int[][] land) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startR, startC});
        visited[startR][startC] = true;
        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];

            p.addAmount(1);
            p.getColumns().add(c);

            for (int[] d : direction) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr >= 0 && nc >= 0 && nr < land.length && nc < land[0].length) {
                    if (!visited[nr][nc] && land[nr][nc] == 1) {
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }
    }

    public static class Petroleum {

        private int amount;
        private Set<Integer> columns;

        public Petroleum() {
            amount = 0;
            columns = new HashSet<>();
        }

        public int getAmount() {
            return amount;
        }

        public Set<Integer> getColumns() {
            return columns;
        }

        public void addAmount(int amount) {
            this.amount += amount;
        }
    }
}
