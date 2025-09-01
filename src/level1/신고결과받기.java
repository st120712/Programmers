package level1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 신고결과받기 {

    public static void main(String[] args) {

    }

    public static int[] solution(String[] idList, String[] report, int k) {
        Map<String, Set<String>> reporter = new HashMap<>();
        Map<String, Integer> msgCnt = new HashMap<>();
        for (String id : idList) {
            reporter.put(id, new HashSet<>());
            msgCnt.put(id, 0);
        }

        for (String r : report) {
            String[] s = r.split(" ");
            String from = s[0];
            String to = s[1];

            reporter.get(to).add(from);
        }

        for (String key : reporter.keySet()) {
            if (reporter.get(key).size() >= k) {
                for (String p : reporter.get(key)) {
                    msgCnt.put(p, msgCnt.get(p) + 1);
                }
            }
        }

        int[] ans = new int[idList.length];

        for (int i = 0; i < idList.length; i++) {
            ans[i] = msgCnt.get(idList[i]);
        }

        return ans;
    }
}
