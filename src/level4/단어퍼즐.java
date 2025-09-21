package level4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 단어퍼즐 {

    public static void main(String[] args) {
        String[] strs = {"app", "ap", "p", "l", "e", "ple", "pp"};
        String t = "apple";
        System.out.println(solution(strs, t));
    }

    public static int solution(String[] strs, String t) {
        int n = t.length();
        int[] dp = new int[n + 1];
        int INF = 1_000_000_000;
        Arrays.fill(dp, INF);
        dp[0] = 0;

        Map<Character, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char c = s.charAt(0);
            List<String> list = map.getOrDefault(c, new ArrayList<>());
            list.add(s);
            map.put(c, list);
        }

        for (int i = 0; i < n; i++) {
            if (dp[i] == INF) {
                continue;
            }
            char c = t.charAt(i);

            for (String s : map.get(c)) {
                int len = s.length();
                if (i + len <= n && t.startsWith(s, i)) {
                    dp[i + len] = Math.min(dp[i + len], dp[i] + 1);
                }
            }
        }
        return dp[n] == INF ? -1 : dp[n];
    }
}
