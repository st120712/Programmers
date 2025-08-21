package level3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 보석쇼핑 {

    public static void main(String[] args) {
        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};

        System.out.println(Arrays.toString(solution(gems)));
    }

    public static int[] solution(String[] gems) {
        Map<String, Integer> cart = new HashMap<>();
        for (String gem : gems) {
            if (!cart.containsKey(gem)) {
                cart.put(gem, 0);
            }
        }

        int n = gems.length;
        int left = 0;
        int bestL = 0;
        int bestR = n;

        int missing = cart.size();

        for (int right = 0; right < n; right++) {
            String curGem = gems[right];
            if (cart.get(curGem) == 0) {
                missing--;
            }
            cart.put(curGem, cart.get(curGem) + 1);

            while (missing == 0) {
                if (bestR - bestL > right - left) {
                    bestL = left;
                    bestR = right;
                }
                String g = gems[left++];
                if (cart.get(g) == 1) {
                    missing++;
                }
                cart.put(g, cart.get(g) - 1);
            }
        }

        return new int[]{bestL + 1, bestR + 1};
    }
}
