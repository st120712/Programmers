package level2;

public class 아날로그시계 {

    public static void main(String[] args) {
        int h1 = 0;
        int m1 = 0;
        int s1 = 0;
        int h2 = 23;
        int m2 = 59;
        int s2 = 59;

        System.out.println(solution(h1, m1, s1, h2, m2, s2));
    }

    public static int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        // 0:0:0 기준으로 시작 시각과 종료 시각에 대한 겹침 개수(k)를 구하고 k_t1 - k_t2 + 1을 구하여 사이 시간 동안의 겹침 개수를 출력
        // 겹침 개수는 상대속도를 이용하여 1회 겹침의 주기를 구하고 전체 초에서 나누어 계산
        /*
         시침 : (1 / 120) 도 / 초
         분침 : (1 / 10) 도 / 초

         시-초 상대속도 : 6 - (1 / 120) 도 / 초 => (719 / 120) 도 / 초
         분-초 상대속도 : 6 - (1 / 10) 도 / 초 => (59 / 10) 도 / 초

         시-초 겹침주기 : 360 도(한 바퀴) / 상대속도 => (360 * 120  / 719) 초 / 회 => (43200 / 719) 초 / 회 => 43200 초 마다 719회 겹침
         분-초 겹침주기 : (360 * 10 / 59) 초 / 회 => (3600 / 59) 초 / 회 => 3600 초 마다 59회 겹침
         시-분-초 겹침주기 : 12 시간 / 회 => 43200 초 / 회 => 43200 초 마다 1회 겹침

         총 겹침 : (시-초 겹침) + (분-초 겹침) - (시-분-초 겹침)
         */
        long t1 = toSeconds(h1, m1, s1);
        long t2 = toSeconds(h2, m2, s2);

        long sm = countHits(59, 3600, t1, t2);
        long sh = countHits(719, 43200, t1, t2);
        long all = countHits(1, 43200, t1, t2);

        long answer = sm + sh - all;

        return (int) answer;
    }

    public static long toSeconds(int h, int m, int s) {
        return h * 3600 + m * 60 + s;
    }

    public static long countHits(long num, long den, long t1, long t2) {
        long a = ceilDiv(num * t1, den); // ceil
        long b = (num * t2) / den; // floor

        return Math.max(0, b - a + 1);
    }

    public static long ceilDiv(long x, long y) {
        return (x + y - 1) / y;
    }
}
