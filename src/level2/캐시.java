package level2;

import java.util.ArrayDeque;
import java.util.Queue;

public class 캐시 {

    public static void main(String[] args) {
        int cacheSize = 5;
        String[] cities = {"a", "b", "c", "a"};
        System.out.println(solution(cacheSize, cities));
    }

    static int solution(int cacheSize, String[] cities) {
        Queue<String> q = new ArrayDeque<>();

        for (int i = 0; i < cities.length; i++) {
            cities[i] = cities[i].toLowerCase();
        }

        int ans = 0;
        for (int i = 0; i < cities.length; i++) {
            String cur = cities[i];
            if (!q.contains(cur)) {
                ans += 5;
                if (q.size() == cacheSize) {
                    q.poll();
                }
            } else {
                ans++;
                q.remove(cur);
            }
            if (q.size() < cacheSize) {
                q.offer(cur);
            }
        }

        return ans;
    }
}
