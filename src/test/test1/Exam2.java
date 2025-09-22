package test.test1;

public class Exam2 {

    public static void main(String[] args) {
        int cap = 4;
        int n = 5;
        int[] deliveries = {1, 0, 3, 1, 2};
        int[] pickups = {0, 3, 0, 4, 0};
        System.out.println(solution(cap, n, deliveries, pickups));
    }

    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long ans = 0;

        int di = n - 1;
        int pi = n - 1;

        while (di >= 0 && deliveries[di] == 0) {
            di--;
        }
        while (pi >= 0 && pickups[pi] == 0) {
            pi--;
        }

        while (di >= 0 || pi >= 0) {
            int far = Math.max(di, pi) + 1;
            ans += far * 2;

            int load = 0;
            while (di >= 0 && load < cap) {
                if (deliveries[di] == 0) {
                    di--;
                    continue;
                }
                int take = Math.min(cap - load, deliveries[di]);
                deliveries[di] -= take;
                load += take;
                if (deliveries[di] == 0) {
                    while (di >= 0 && deliveries[di] == 0) {
                        di--;
                    }
                }
            }

            load = 0;
            while (pi >= 0 && load < cap) {
                if (pickups[pi] == 0) {
                    pi--;
                    continue;
                }
                int take = Math.min(cap - load, pickups[pi]);
                pickups[pi] -= take;
                load += take;
                if (pickups[pi] == 0) {
                    while (pi >= 0 && pickups[pi] == 0) {
                        pi--;
                    }
                }
            }
        }

        return ans;
    }
}
