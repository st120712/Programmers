package level3;

public class 징검다리건너기 {

    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};

        int k = 3;

        System.out.println(solution(stones, k));
    }

    public static int solution(int[] stones, int k) {
        int lo = 1;
        int hi = 200_000_000;
        int ans = 0;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (isPossible(stones, k, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    public static boolean isPossible(int[] stones, int k, int m) {
        int cnt = 0;

        for (int s : stones) {
            if (s < m) {
                cnt++;
            } else {
                cnt = 0;
            }

            if (cnt == k) {
                return false;
            }
        }

        return true;
    }
}
