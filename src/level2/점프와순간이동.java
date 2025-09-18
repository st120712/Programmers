package level2;

public class 점프와순간이동 {

    public static void main(String[] args) {
        int n = 5000;
        System.out.println(solution(n));
    }

    public static int solution(int n) {
        int ans = 0;

        while (n != 0) {
            if (n % 2 == 1) {
                n--;
                ans++;
            } else {
                n /= 2;
            }
        }
        return ans;
    }
}
