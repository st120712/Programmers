package level2;
import java.util.HashMap;
import java.util.Map;

/**
 * A도둑과 B도둑이 팀을 이루어 모든 물건을 훔치려고 합니다. 단, 각 도둑이 물건을 훔칠 때 남기는 흔적이 누적되면 경찰에 붙잡히기
 * 때문에, 두 도둑 중 누구도 경찰에 붙잡히지 않도록 흔적을 최소화해야 합니다.
 *
 *  *물건을 훔칠 때 조건은 아래와 같습니다.
 *
 *  *물건 i를 훔칠 때, A도둑이 훔치면 info[i][0]개의 A에 대한 흔적을 남깁니다. B도둑이 훔치면 info[i][1]개의 B에
 * 대한 흔적을 남깁니다. 각 물건에 대해 A도둑과 B도둑이 남기는 흔적의 개수는 1 이상 3 이하입니다. 경찰에 붙잡히는 조건은 아래와
 * 같습니다.
 *
 *  *A도둑은 자신이 남긴 흔적의 누적 개수가 n개 이상이면 경찰에 붙잡힙니다. B도둑은 자신이 남긴 흔적의 누적 개수가 m개 이상이면
 * 경찰에 붙잡힙니다. 각 물건을 훔칠 때 생기는 흔적에 대한 정보를 담은 2차원 정수 배열 info, A도둑이 경찰에 붙잡히는 최소 흔적
 * 개수를 나타내는 정수 n, B도둑이 경찰에 붙잡히는 최소 흔적 개수를 나타내는 정수 m이 매개변수로 주어집니다. 두 도둑 모두 경찰에
 * 붙잡히지 않도록 모든 물건을 훔쳤을 때, A도둑이 남긴 흔적의 누적 개수의 최솟값을 return 하도록 solution 함수를 완성해
 * 주세요. 만약 어떠한 방법으로도 두 도둑 모두 경찰에 붙잡히지 않게 할 수 없다면 -1을 return해 주세요.
 *
 *
 * 제한사항 1 ≤ info의 길이 ≤ 40 info[i]는 물건 i를 훔칠 때 생기는 흔적의 개수를 나타내며, [A에 대한 흔적 개수, B에
 * 대한 흔적 개수]의 형태입니다. 1 ≤ 흔적 개수 ≤ 3 1 ≤ n ≤ 120 1 ≤ m ≤ 120
 */
public class 완전범죄 {

    public static void main(String[] args) throws Exception {
        int[][] test = {{1, 2}, {2, 3}, {2, 1}};
        int n = 4;
        int m = 4;

        System.out.println(solution(test, n, m));
    }

    static int solution(int[][] info, int n, int m) {
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 0);

        for (int i = 0; i < info.length; i++) {
            int aTrace = info[i][0];
            int bTrace = info[i][1];

            Map<Integer, Integer> nextDp = new HashMap<>();

            for (Map.Entry<Integer, Integer> entry : dp.entrySet()) {
                int bSum = entry.getKey();
                int aSum = entry.getValue();

                int newA = aSum + aTrace;
                if (newA < n) {
                    nextDp.put(bSum, Math.min(nextDp.getOrDefault(bSum, Integer.MAX_VALUE), newA));
                }

                int newB = bSum + bTrace;
                if (newB < m) {
                    nextDp.put(newB, Math.min(nextDp.getOrDefault(newB, Integer.MAX_VALUE), aSum));
                }
            }

            dp = nextDp;
        }

        if (dp.isEmpty()) {
            return -1;
        }

        int minA = Integer.MAX_VALUE;
        for (int a : dp.values()) {
            minA = Math.min(minA, a);
        }
        return minA;
    }
}
