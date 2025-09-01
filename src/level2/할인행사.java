package level2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 할인행사 {

    public static void main(String[] args) {
        String[] want = {"banana", "apple", "rice", "pork", "pot"};
        int[] number = {3, 2, 2, 2, 1};
        String[] discount = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};
        System.out.println(solution(want, number, discount));
    }

    public static int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> wantMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }

        List<Map<String, Integer>> available = new ArrayList<>();
        for (int i = 0; i < discount.length - 9; i++) {
            if (i == 0) {
                Map<String, Integer> temp = new HashMap<>();
                for (int j = 0; j < 10; j++) {
                    temp.put(discount[j], temp.getOrDefault(discount[j], 0) + 1);
                }
                available.add(temp);
            } else {
                Map<String, Integer> temp = new HashMap<>(available.get(available.size() - 1));
                if (temp.get(discount[i - 1]) - 1 == 0) {
                    temp.remove(discount[i - 1]);
                } else {
                    temp.put(discount[i - 1], temp.get(discount[i - 1]) - 1);
                }
                temp.put(discount[i + 9], temp.getOrDefault(discount[i + 9], 0) + 1);
                available.add(temp);
            }
        }

        int ans = 0;

        for (Map<String, Integer> m : available) {
            if (m.equals(wantMap)) {
                ans++;
            }
        }

        return ans;
    }
}
