package test.test5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Exam1 {

    public static void main(String[] args) {
        String[] gems = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
        System.out.println(Arrays.toString(solution(gems)));
    }

    static int[] solution(String[] gems) {
        int totalTypes = new HashSet<>(Arrays.asList(gems)).size();

        Map<String, Integer> windowCounts = new HashMap<>();

        int bestL = 0, bestR = gems.length - 1;
        int left = 0;

        for (int right = 0; right < gems.length; right++) {
            windowCounts.merge(gems[right], 1, Integer::sum);

            while (windowCounts.size() == totalTypes) {
                if ((right - left) < (bestR - bestL)) {
                    bestL = left;
                    bestR = right;
                }

                String leftGem = gems[left++];
                int cnt = windowCounts.get(leftGem) - 1;
                if (cnt == 0) {
                    windowCounts.remove(leftGem);
                } else {
                    windowCounts.put(leftGem, cnt);
                }
            }
        }

        return new int[]{bestL + 1, bestR + 1};
    }
}
