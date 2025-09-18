package level2;

import java.util.HashMap;
import java.util.Map;

public class 롤케이크자르기 {

    public static void main(String[] args) {

    }

    public static int solution(int[] topping) {
        Map<Integer, Integer> leftTopping = new HashMap<>();
        Map<Integer, Integer> rightTopping = new HashMap<>();
        int leftCnt = 0;
        int rightCnt = 0;

        for (int t : topping) {
            int cnt = rightTopping.getOrDefault(t, 0);
            if (cnt == 0) {
                rightCnt++;
            }
            rightTopping.put(t, cnt + 1);
        }

        int ans = 0;

        for (int t : topping) {
            if (leftCnt == rightCnt) {
                ans++;
            }

            if (leftTopping.getOrDefault(t, 0) == 0) {
                leftCnt++;
            }
            if (rightTopping.get(t) == 1) {
                rightCnt--;
            }
            leftTopping.put(t, leftTopping.getOrDefault(t, 0) + 1);
            rightTopping.put(t, rightTopping.get(t) - 1);
        }

        return ans;
    }
}
