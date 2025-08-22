package level1;

import java.util.Arrays;

public class 체육복 {

    public static void main(String[] args) {
        int n = 5;
        int[] lost = {2, 4};
        int[] reserve = {3};

        System.out.println(solution(n, lost, reserve));
    }

    public static int solution(int n, int[] lost, int[] reserve) {
        Arrays.sort(lost);
        int[] all = new int[n + 1];
        Arrays.fill(all, 1);
        for (int r : reserve) {
            all[r] = 2;
        }
        for (int l : lost) {
            all[l]--;
        }

        for (int i = 1; i <= n; i++) {
            if (i == 1 && all[i] == 2 && all[i + 1] == 0) {
                all[1]--;
                all[2]++;
            } else if (all[i] == 2) {
                if (all[i - 1] == 0) {
                    all[i]--;
                    all[i - 1]++;
                } else if (i < n && all[i + 1] == 0) {
                    all[i]--;
                    all[i + 1]++;
                }
            }
        }

        int ans = 0;

        System.out.println(Arrays.toString(all));

        for (int i = 1; i <= n; i++) {
            if (all[i] >= 1) {
                ans++;
            }
        }

        return ans;
    }
}
