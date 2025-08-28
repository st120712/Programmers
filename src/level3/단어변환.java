package level3;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class 단어변환 {

    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};

        System.out.println(solution(begin, target, words));
    }

    public static int solution(String begin, String target, String[] words) {
        Map<String, Integer> changed = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            changed.put(words[i], 0);
        }
        changed.put(begin, 0);

        Queue<String> q = new ArrayDeque<>();
        q.offer(begin);

        while (!q.isEmpty()) {
            String cur = q.poll();

            for (String w : words) {
                if (changed.get(w) > 0) {
                    continue;
                }

                int equalCnt = 0;

                for (int i = 0; i < w.length(); i++) {
                    if (w.charAt(i) == cur.charAt(i)) {
                        equalCnt++;
                    }
                }

                if (w.length() - equalCnt == 1) {
                    changed.put(w, changed.get(cur) + 1);
                    q.offer(w);
                }
            }
        }

        return changed.getOrDefault(target, 0);
    }
}
