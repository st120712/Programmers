package level2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 영어끝말잇기 {

    public static void main(String[] args) {
        int n = 2;
        String[] words = {"hello", "one", "even", "never", "now", "world", "draw"};
        System.out.println(Arrays.toString(solution(n, words)));
    }

    public static int[] solution(int n, String[] words) {
        int[] cnt = new int[n + 1];
        Set<String> set = new HashSet<>();
        String preString = null;

        for (int i = 0; i < words.length; i++) {
            int num = i % n + 1;
            cnt[num]++;

            if (preString == null) {
                preString = words[i];
            } else {
                char preC = preString.charAt(preString.length() - 1);
                char nowC = words[i].charAt(0);
                if (preC == nowC && !set.contains(words[i])) {
                    preString = words[i];
                } else {
                    return new int[]{num, cnt[num]};
                }
            }
            set.add(words[i]);
        }

        return new int[]{0, 0};
    }
}
