package level3;

import java.util.Arrays;

public class 외벽점검 {

    public static void main(String[] args) {
        int n = 12;
        int[] weak = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};
        System.out.println(solution(n, weak, dist));
    }

    static int[] Weak;

    public static int solution(int n, int[] weak, int[] dist) {
        int w = weak.length;
        Weak = new int[w * 2];
        for (int i = 0; i < w; i++) {
            Weak[i] = weak[i];
            Weak[i + w] = weak[i] + n;
        }

        Arrays.sort(dist);
        reverse(dist);

        for (int m = 1; m <= dist.length; m++) {
            boolean[] used = new boolean[dist.length];
            int[] pick = new int[m];

            if (tryWithM(w, dist, used, pick, 0)) {
                return m;
            }
        }

        return -1;
    }

    static boolean tryWithM(int w, int[] dist, boolean[] used, int[] pick, int depth) {
        if (depth == pick.length) {
            for (int s = 0; s < w; s++) {
                if (canCover(w, s, pick)) {
                    return true;
                }
            }
            return false;
        }
        for (int i = 0; i < dist.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            pick[depth] = dist[i];
            if (tryWithM(w, dist, used, pick, depth + 1)) {
                return true;
            }
            used[i] = false;
        }
        return false;
    }

    static boolean canCover(int w, int start, int[] pick) {
        int idx = start;
        int endIdx = start + w;

        for (int p = 0; p < pick.length && idx < endIdx; p++) {
            int coverEnd = Weak[idx] + pick[p];
            while (idx < endIdx && Weak[idx] <= coverEnd) {
                idx++;
            }
        }
        return idx >= endIdx;
    }

    static void reverse(int[] a) {
        for (int i = 0, j = a.length - 1; i < j; i++, j--) {
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }
}
