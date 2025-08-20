package level2;

import java.util.HashSet;
import java.util.Set;

public class 시소짝꿍 {

    public static void main(String[] args) {
        int[] weight = {100, 180, 360, 100, 270};

        System.out.println(solution(weight));
    }

    public static long solution(int[] weights) {

        long count = 0;
        Set<Integer> set = new HashSet<>();
        Set<Integer> ts = new HashSet<>();

        for (int w : weights) {
            if (!ts.add(w)) {
                count -= 2;
            }
        }

        for (int w : weights) {
            int[] tws = {w * 2, w * 3, w * 4};

            for (int tw : tws) {
                if (!set.add(tw)) {
                    count++;
                }
            }
        }

        return count;
    }
}
