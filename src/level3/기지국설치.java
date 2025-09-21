package level3;

public class 기지국설치 {

    public static void main(String[] args) {
        int n = 16;
        int[] stations = {9};
        int w = 2;
        System.out.println(solution(n, stations, w));
    }

    public static int solution(int n, int[] stations, int w) {
        int wide = 2 * w + 1;
        int pos = 1;
        int ans = 0;

        for (int s : stations) {
            int coverl = Math.max(1, s - w);
            int coverR = Math.max(1, s + w);

            if (pos < coverl) {
                int len = coverl - pos;
                ans += (len + wide - 1) / wide;
            }
            pos = Math.max(pos, coverR + 1);
            if (pos > n) {
                break;
            }
        }

        if (pos <= n) {
            int len = n - pos + 1;
            ans += (len + wide - 1) / wide;
        }
        return ans;
    }
}
