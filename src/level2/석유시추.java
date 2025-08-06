package level2;

import java.util.HashSet;
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

        for (int y = 0; y < land.length; y++) {
            for (int x = 0; x < land[0].length; x++) {
                if (visited[y][x]) {
                    continue;
                }
                if (land[y][x] == 1) {
                    Petroleum p = new Petroleum();
                    dfs(x, y, p, visited, land);
                    for (int c : p.columns) {
                        columnOil[c] += p.getAmount();
                    }
                }
            }
        }

        for (int oil : columnOil) {
            if (maxOil < oil) {
                maxOil = oil;
            }
        }

        return maxOil;
    }

    public static void dfs(int x, int y, Petroleum p, boolean[][] visited, int[][] land) {
        if (y < 0 || x < 0 || y >= land.length || x >= land[0].length) {
            return;
        }
        if (visited[y][x]) {
            return;
        }
        if (land[y][x] == 0) {
            return;
        }

        visited[y][x] = true;
        p.addAmount(land[y][x]);
        p.getColumns().add(x);

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int[] d : directions) {
            int dx = x + d[0];
            int dy = y + d[1];
            dfs(dx, dy, p, visited, land);
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
