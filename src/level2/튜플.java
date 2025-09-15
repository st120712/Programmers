package level2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;

public class 튜플 {

    public static void main(String[] args) {
        String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
        System.out.println(Arrays.toString(solution(s)));
    }

    public static int[] solution(String s) {
        String[] parts = s.substring(2, s.length() - 2).split("\\},\\{");

        Arrays.sort(parts, Comparator.comparing(String::length));

        LinkedHashSet<Integer> ordered = new LinkedHashSet<>();
        for (String part : parts) {
            for (String token : part.split(",")) {
                ordered.add(Integer.parseInt(token));
            }
        }

        return ordered.stream().mapToInt(Integer::intValue).toArray();
    }
}
