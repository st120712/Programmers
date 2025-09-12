package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 튜플 {

    public static void main(String[] args) {
        String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
        System.out.println(Arrays.toString(solution(s)));
    }

    public static int[] solution(String s) {
        s = s.substring(1, s.length() - 1);
        String[] arr = s.split("},");
        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            List<Integer> temp = new ArrayList<>();
            arr[i] = arr[i].split("\\{")[1];
            if (i == arr.length - 1) {
                arr[i] = arr[i].split("\\}")[0];
            }
            Arrays.stream(arr[i].split(",")).mapToInt(Integer::parseInt).forEach(temp::add);
            list.add(temp);
        }

        list.sort((a, b) -> Integer.compare(a.size(), b.size()));

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            for (int n : list.get(i)) {
                if (!ans.contains(n)) {
                    ans.add(n);
                }
            }
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
