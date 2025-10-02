package level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 주사위고르기 {

    public static void main(String[] args) {
        int[][] dice = {{40, 41, 42, 43, 44, 45}, {43, 43, 42, 42, 41, 41}, {1, 1, 80, 80, 80, 80}, {70, 70, 1, 1, 70, 70}};
        System.out.println(Arrays.toString(solution(dice)));
    }

    static int[] solution(int[][] dice) {
        int n = dice.length;
        int k = n / 2;

        long bestWins = -1;
        int[] bestPick = null;

        List<int[]> combs = new ArrayList<>();
        combDfs(n, k, 0, new int[k], 0, combs);

        for (int[] aPick : combs) {
            boolean[] used = new boolean[n];
            for (int v : aPick) {
                used[v] = true;
            }
            int[] bPick = new int[k];
            for (int i = 0, j = 0; i < n; i++) {
                if (!used[i]) {
                    bPick[j++] = i;
                }
            }
            int[] sa = allSumSorted(dice, aPick);
            int[] sb = allSumSorted(dice, bPick);

            long wins = countStrickWins(sa, sb);
            if (wins > bestWins) {
                bestWins = wins;
                bestPick = aPick.clone();
            }
        }
        int[] ans = Arrays.stream(bestPick).map(i -> i + 1).toArray();
        Arrays.sort(ans);

        return ans;
    }

    static void combDfs(int n, int k, int depth, int[] comb, int size, List<int[]> combs) {
        if (size >= k) {
            combs.add(Arrays.copyOf(comb, k));
            return;
        }

        for (int i = depth; i < n; i++) {
            comb[size] = i;
            combDfs(n, k, i + 1, comb, size + 1, combs);
        }
    }

    static int[] allSumSorted(int[][] dice, int[] pick) {
        int[] sums = new int[]{0};

        for (int idx : pick) {
            int[] faces = dice[idx];
            int[] next = new int[sums.length * 6];
            int p = 0;
            for (int s : sums) {
                for (int f : faces) {
                    next[p++] = s + f;
                }
            }
            sums = next;
        }

        Arrays.sort(sums);
        return sums;
    }

    static long countStrickWins(int[] sa, int[] sb) {
        long wins = 0L;
        for (int a : sa) {
            int cnt = lowerBound(sb, a);
            wins += cnt;
        }
        return wins;
    }

    static int lowerBound(int[] arr, int key) {
        int lo = 0;
        int hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] < key) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
