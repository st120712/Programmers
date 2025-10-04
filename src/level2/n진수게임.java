package level2;

public class n진수게임 {

    // 1. 숫자 0부터 차례대로 말함
    // 1-1. 두자리부턴 한자리씩 끊어서 말함
    // n : 진수, t : 말해야하는 숫자 개수, m : 플레이어 수, p : 플레이 순서
    // 현재 자릿수, 진수 더하기
    // 다음 진수에서 몇번째 자리를 말해야하는 지 체크
    public static void main(String[] args) {
        int n = 16, t = 16, m = 2, p = 1;
        System.out.println(solution(n, t, m, p));
    }

    static String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        sb.append(num);
        for (int i = 0; i < t * m; i++) {
            num += 1;
            sb.append(Integer.toString(num, n));
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < t; i++) {
            ans.append(sb.charAt(p - 1));
            p += m;
        }

        return ans.toString().toUpperCase();
    }
}
