package level2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 귤고르기 {

    public static void main(String[] args) {
        int k = 6;
        int[] tangerine = {1, 3, 2, 5, 4, 5, 2, 3};
        System.out.println(solution(k, tangerine));
    }

    public static int solution(int k, int[] tangerine) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int t : tangerine) {
            cnt.put(t, cnt.getOrDefault(t, 0) + 1);
        }
        List<Integer> sizes = new ArrayList<>(cnt.keySet());
        sizes.sort((a, b) -> {
            return Integer.compare(cnt.get(b), cnt.get(a));
        });

        int num = 0;
        int ans = 0;
        for (int s : sizes) {
            int n = cnt.get(s);
            if (num + n < k) {
                num += n;
                ans++;
            } else {
                ans++;
                break;
            }
        }

        return ans;
    }
}
