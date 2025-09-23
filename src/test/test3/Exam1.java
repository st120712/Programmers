package test.test3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Exam1 {

    public static void main(String[] args) {
        String[] userId = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] baanedId = {"fr*d*", "*rodo", "******", "******"};
        System.out.println(solution(userId, baanedId));
    }

    static Set<Set<String>> ans = new HashSet<>();

    static int solution(String[] userId, String[] bannedId) {

        int un = userId.length;
        int bn = bannedId.length;
        List<String>[] list = new ArrayList[bn];

        for (int i = 0; i < bn; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < un; i++) {
            for (int j = 0; j < bn; j++) {
                if (isAble(userId[i], bannedId[j])) {
                    list[j].add(userId[i]);
                }
            }
        }

        dfs(0, list, new HashSet<>());

        return ans.size();
    }

    static void dfs(int depth, List<String>[] list, Set<String> set) {
        if (depth == list.length) {
            if (set.size() == list.length) {
                ans.add(new HashSet<>(set));
            }
            return;
        }

        List<String> temp = list[depth];
        for (int i = 0; i < temp.size(); i++) {
            if (set.contains(temp.get(i))) {
                continue;
            }
            set.add(temp.get(i));
            dfs(depth + 1, list, set);
            set.remove(temp.get(i));
        }
    }

    static boolean isAble(String uid, String bid) {
        if (uid.length() != bid.length()) {
            return false;
        }

        for (int i = 0; i < uid.length(); i++) {
            if (bid.charAt(i) == '*') {
                continue;
            }

            if (uid.charAt(i) != bid.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
