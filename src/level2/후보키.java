package level2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 후보키 {

    public static void main(String[] args) {
        String[][] relation = {
            {"a", "aa"},
            {"aa", "a"},
            {"a", "a"},};

        System.out.println(solution(relation));
    }

    static int solution(String[][] relation) {
        int n = relation.length;
        int m = relation[0].length;

        List<Integer> combos = new ArrayList<>();
        for (int mask = 1; mask < (1 << m); mask++) {
            combos.add(mask);
        }

        combos.sort(Comparator.comparingInt(Integer::bitCount));

        List<Integer> candidateKeys = new ArrayList<>();

        for (int mask : combos) {
            boolean minimal = true;
            for (int ck : candidateKeys) {
                if ((mask & ck) == ck) {
                    minimal = false;
                    break;
                }
            }
            if (!minimal) {
                continue;
            }

            Set<String> seen = new HashSet<>();
            for (int r = 0; r < n; r++) {
                StringBuilder sb = new StringBuilder();
                for (int c = 0; c < m; c++) {
                    if ((mask & (1 << c)) != 0) {
                        sb.append('#').append(c).append(':').append(relation[r][c]);
                    }
                }
                seen.add(sb.toString());
            }

            if (seen.size() == n) {
                candidateKeys.add(mask);
            }
        }

        return candidateKeys.size();
    }
}
