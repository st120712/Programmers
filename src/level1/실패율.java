package level1;

import java.util.Arrays;

public class 실패율 {

    public static void main(String[] args) {
        int N = 4;
        int[] stages = {4, 4, 4, 4, 4};

        System.out.println(Arrays.toString(solution(N, stages)));
    }

    public static int[] solution(int N, int[] stages) {
        double[][] a = new double[N + 1][2];
        double[] b = new double[N + 1];

        for (int i = 0; i < N + 1; i++) {
            a[i][0] = i + 1;
        }
        for (int s : stages) {
            a[s - 1][1]++;
        }

        for (int i = 0; i < N; i++) {
            if (i == 0) {
                b[i] = stages.length;
            } else {
                b[i] = b[i - 1] - a[i - 1][1];
            }
        }

        for (int i = 0; i < N; i++) {
            if (a[i][1] == 0) {
                continue;
            }
            a[i][1] /= b[i];
        }

        double[][] c = Arrays.copyOfRange(a, 0, N);

        Arrays.sort(c, (x, y) -> {
            if (x[1] != y[1]) {
                return Double.compare(y[1], x[1]);
            }

            return Double.compare(x[0], y[0]);
        });

        int[] ans = new int[N];

        for (int i = 0; i < N; i++) {
            ans[i] = (int) c[i][0];
        }

        return ans;
    }
}
