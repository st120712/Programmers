package level2;

import java.util.ArrayList;
import java.util.List;

public class NQueen {

    public static void main(String[] args) {
        int n = 4;
        System.out.println(solution(n));
    }

    static int ans = 0;

    public static int solution(int n) {
        setQueen(n, 0, 0, new ArrayList<>());
        return ans;
    }

    public static void setQueen(int n, int cnt, int row, List<int[]> qList) {
        if (cnt == n) {
            ans++;
            return;
        }
        for (int i = 0; i < n; i++) {
            boolean available = true;
            int[] position = new int[]{row, i};
            for (int[] q : qList) {
                if (!isValid(position, q[0], q[1])) {
                    available = false;
                    break;
                }
            }
            if (available) {
                qList.add(position);
                setQueen(n, cnt + 1, row + 1, qList);
                qList.remove(position);
            }
        }
    }

    static boolean isValid(int[] position, int row, int col) {
        return !(inRow(position, row) || inCol(position, col) || inDiagonal(position, row, col));
    }

    static boolean inRow(int[] position, int row) {
        return row == position[0];
    }

    static boolean inCol(int[] position, int col) {
        return col == position[1];
    }

    static boolean inDiagonal(int[] position, int row, int col) {
        return Math.abs(row - position[0]) == Math.abs(col - position[1]);
    }
}
