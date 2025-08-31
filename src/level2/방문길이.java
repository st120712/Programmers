package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 방문길이 {

    public static void main(String[] args) {
        String dirs = "LULLLLLLU";
        System.out.println(solution(dirs));
    }

    public static int solution(String dirs) {
        List<Edge> list = new ArrayList<>();

        int[] cur = {0, 0};
        for (char ch : dirs.toCharArray()) {
            int r = cur[0];
            int c = cur[1];
            int nr = r;
            int nc = c;
            if (ch == 'U') {
                nr += 1;
            }
            if (ch == 'D') {
                nr -= 1;
            }
            if (ch == 'L') {
                nc -= 1;
            }
            if (ch == 'R') {
                nc += 1;
            }

            if (nr >= -5 && nr <= 5 && nc >= -5 && nc <= 5) {
                if (!list.contains(new Edge(new int[]{r, c}, new int[]{nr, nc}))) {
                    list.add(new Edge(new int[]{r, c}, new int[]{nr, nc}));
                }
                cur[0] = nr;
                cur[1] = nc;
            }
        }
        return list.size();
    }

    public static class Edge {

        int[] from;
        int[] to;

        public Edge(int[] from, int[] to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object obj) {
            if (Arrays.toString(from).equals(Arrays.toString(((Edge) obj).from))
                    && Arrays.toString(to).equals(Arrays.toString(((Edge) obj).to))) {
                return true;
            }
            if (Arrays.toString(from).equals(Arrays.toString(((Edge) obj).to))
                    && Arrays.toString(to).equals(Arrays.toString(((Edge) obj).from))) {
                return true;
            }

            return false;
        }
    }
}
