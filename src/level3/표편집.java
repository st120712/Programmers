package level3;

import java.util.Arrays;
import java.util.Stack;

public class 표편집 {

    public static void main(String[] args) {
        int n = 8;
        int k = 2;
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
        System.out.println(solution(n, k, cmd));
    }

    public static String solution(int n, int k, String[] cmd) {
        boolean[] table = new boolean[n];
        Arrays.fill(table, true);
        Stack<Integer> trash = new Stack<>();

        int[][] g = new int[n][2];
        for (int i = 0; i < n; i++) {
            g[i] = new int[]{i - 1, i + 1};
        }

        for (String c : cmd) {
            if (c.startsWith("C")) {
                trash.push(k);
                table[k] = false;

                int up = g[k][0];
                int down = g[k][1];

                if (up >= 0) {
                    g[up][1] = down;
                }

                if (down < n) {
                    g[down][0] = up;
                    k = down;
                } else {
                    k = up;
                }

            } else if (c.startsWith("Z")) {
                int t = trash.pop();
                table[t] = true;
                int up = g[t][0];
                int down = g[t][1];

                if (up >= 0) {
                    g[up][1] = t;
                }
                if (down < n) {
                    g[down][0] = t;
                }
            } else {
                String c1 = c.split(" ")[0];
                int c2 = Integer.parseInt(c.split(" ")[1]);

                if (c1.equals("U")) {
                    for (int i = 0; i < c2; i++) {
                        k = g[k][0];
                    }
                } else {
                    for (int i = 0; i < c2; i++) {
                        k = g[k][1];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (boolean b : table) {
            if (b) {
                sb.append("O");
            } else {
                sb.append("X");
            }
        }

        return sb.toString();
    }

}
