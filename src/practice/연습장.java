package practice;

import java.util.HashMap;
import java.util.Map;

public class 연습장 {

    public static void main(String[] args) {
    }

    public static String solution(String[] participant, String[] completion) {
        Map<String, Integer> m = new HashMap<>();
        for (String p : participant) {
            m.put(p, m.getOrDefault(p, 0) + 1);
        }

        for (String c : completion) {
            m.put(c, m.get(c) - 1);
        }

        for (String k : m.keySet()) {
            if (m.get(k) == 1) {
                return k;
            }
        }

        return "null";
    }
}
