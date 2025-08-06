package level2;

import java.util.LinkedList;
import java.util.Queue;

public class 지게차와크레인 {

    public static void main(String[] args) {
        String[] storage = {"HAH", "HBH", "HHH", "HAH", "HBH"};

        String[] requests = {"C", "B", "B", "B", "B", "H"};

        System.out.println(floodFill(storage, requests));
    }

    public static int floodFill(String[] storage, String[] requests) {
        int answer = 0;

        char[][] grid = convertToGrid(storage);

        for (String request : requests) {
            char[][] newGrid = {};

            if (request.length() == 1) {
                newGrid = forklift(grid, request.charAt(0));
            }

            if (request.length() == 2 && request.charAt(0) == request.charAt(1)) {
                newGrid = crane(grid, request.charAt(0));
            }

            grid = newGrid;
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != '0') {
                    answer += 1;
                }
            }
        }

        return answer;
    }

    public static char[][] convertToGrid(String[] storage) {
        int row = storage.length + 2;
        int column = storage[0].length() + 2;
        char[][] grid = new char[row][column];

        for (int i = 0; i < column; i++) {
            grid[0][i] = '0';
            grid[row - 1][i] = '0';
        }

        for (int i = 1; i < row - 1; i++) {
            grid[i] = ('0' + storage[i - 1] + '0').toCharArray();
        }

        return grid;
    }

    public static char[][] forklift(char[][] grid, char target) {
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};

        int row = grid.length;
        int column = grid[0].length;

        boolean[][] accessible = new boolean[row][column];
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if ((i == 0 || i == row - 1 || j == 0 || j == column - 1) && grid[i][j] == '0') {
                    accessible[i][j] = true;
                    q.add(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] pos = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];

                if (nx < 0 || nx > row - 1 || ny < 0 || ny > column - 1) {
                    continue;
                }

                if (grid[nx][ny] == '0' && !accessible[nx][ny]) {
                    accessible[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }

        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < column - 1; j++) {
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (nx < 0 || nx > row - 1 || ny < 0 || ny > column - 1) {
                        continue;
                    }

                    if (grid[i][j] == target && accessible[nx][ny]) {
                        grid[i][j] = '0';
                        break;
                    }
                }
            }
        }

        return grid;
    }

    public static char[][] crane(char[][] grid, char target) {
        int row = grid.length;
        int column = grid[0].length;

        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < column - 1; j++) {
                if (grid[i][j] == target) {
                    grid[i][j] = '0';
                }
            }
        }

        return grid;
    }
}
