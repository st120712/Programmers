
public class 서버증설횟수 {

    public static void main(String[] args) {
        int[] players = {0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 5, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1};
        int m = 1;
        int k = 1;

        System.out.println(solution(players, m, k));
    }

    static int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] expansions = new int[players.length];

        for (int i = 0; i < players.length; i++) {
            int needServer = players[i] / m;
            int curServer = 0;

            if (i < k - 1) {
                for (int j = 0; j < i; j++) {
                    curServer += expansions[j];
                }
            } else {
                for (int j = i - k + 1; j < i; j++) {
                    curServer += expansions[j];
                }
            }

            if (needServer - curServer > 0) {
                expansions[i] = needServer - curServer;
            } else {
                expansions[i] = 0;
            }
        }

        for (int i = 0; i < expansions.length; i++) {
            answer += expansions[i];
        }

        return answer;
    }
}
