package test.test2;

import java.util.HashMap;
import java.util.Map;

public class Exam3 {

    public static void main(String[] args) {

    }

    static int solution(int[] numbers) {
        Map<Integer, Boolean> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(i, false);
        }

        for (int i : numbers) {
            map.put(i, true);
        }

        int ans = 0;

        for (Map.Entry<Integer, Boolean> e : map.entrySet()) {
            if (!e.getValue()) {
                ans += e.getKey();
            }
        }

        return ans;
    }
}
