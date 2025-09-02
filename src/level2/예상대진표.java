package level2;

public class 예상대진표 {

    public static void main(String[] args) {

    }

    public static int solution(int n, int a, int b) {
        int ans = 0;
        while (a != b) {
            a = next(a);
            b = next(b);
            ans++;
        }
        return ans;
    }

    public static int next(int n) {
        if (n % 2 == 0) {
            return n / 2;
        } else {
            return n / 2 + 1;
        }
    }
}
