package level2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 시소짝꿍 {

    public static void main(String[] args) {
        int[] weight = {100, 180, 360, 100, 270};

        System.out.println(solution(weight));
    }

    public static long solution(int[] weights) {
        Map<Integer, Integer> wCntMap = new HashMap<>();
        for (int w : weights) {
            wCntMap.put(w, wCntMap.getOrDefault(w, 0) + 1);
        }

        Integer[] wList = wCntMap.keySet().toArray(new Integer[0]);
        Arrays.sort(wList);

        long ans = 0;

        for (int w : wList) {
            long x = wCntMap.get(w);
            ans += x * (x - 1) / 2;
        }

        for (int i = 0; i < wList.length; i++) {
            int a = wList[i];
            long ca = wCntMap.get(a);

            if (a % 2 == 0) {
                int b = a * 3 / 2;
                if (b > a && wCntMap.containsKey(b)) {
                    ans += ca * wCntMap.get(b);
                }
            }

            {
                int b = a * 2;
                if (b > a && wCntMap.containsKey(b)) {
                    ans += ca * wCntMap.get(b);
                }
            }

            if (a % 3 == 0) {
                int b = a * 4 / 3;
                if (b > a && wCntMap.containsKey(b)) {
                    ans += ca * wCntMap.get(b);
                }
            }
        }

        return ans;
    }
}
