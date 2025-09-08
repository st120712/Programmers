package level2;

import java.util.Arrays;

public class 양궁대회 {

    public static void main(String[] args) {
        int n = 5;
        int[] info = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};

        System.out.println(Arrays.toString(solution(n, info)));
    }

    static int bestDiff;
    static int[] best;

    public static int[] solution(int n, int[] info) {
        bestDiff = -1;
        best = null;

        dfs(0, n, new int[11], info);

        if (best == null) {
            return new int[]{-1};
        }
        return best;
    }

    static void dfs(int idx, int left, int[] lion, int[] info) {
        if (idx == 10) {
            lion[10] = left;

            evaluateAndUpdate(lion, info);

            lion[10] = 0;
            return;
        }

        int need = info[idx] + 1;

        if (left >= need) {
            lion[idx] = need;
            dfs(idx + 1, left - need, lion, info);
            lion[idx] = 0;
        }

        dfs(idx + 1, left, lion, info);
    }

    static void evaluateAndUpdate(int[] lion, int[] apeach) {
        int lionScore = 0;
        int apeachScore = 0;

        for (int i = 0; i < 11; i++) {
            if (lion[i] == 0 && apeach[i] == 0) {
                continue;
            }
            if (lion[i] > apeach[i]) {
                lionScore += 10 - i;
            } else {
                apeachScore += 10 - i;
            }
        }

        int diff = lionScore - apeachScore;
        if (diff <= 0) {
            return;
        }
        if (diff > bestDiff) {
            bestDiff = diff;
            best = Arrays.copyOf(lion, 11);
        } else if (diff == bestDiff) {
            if (isBetterTieBreaker(lion, best)) {
                best = Arrays.copyOf(lion, 11);
            }
        }
    }

    static boolean isBetterTieBreaker(int[] cand, int[] curBest) {
        for (int i = 10; i >= 0; i--) {
            if (cand[i] != curBest[i]) {
                return cand[i] > curBest[i];
            }
        }
        return false;
    }
}
