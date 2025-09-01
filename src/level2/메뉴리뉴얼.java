package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 메뉴리뉴얼 {

    public static void main(String[] args) {
        String[] orders = {"XYZ", "XWY", "WXA"};
        int[] course = {2, 3, 4};
        System.out.println(Arrays.toString(solution(orders, course)));
    }

    public static String[] solution(String[] orders, int[] course) {
        List<String> ans = new ArrayList<>();

        for (int n : course) {
            List<String> list = maxComb(orders, n);
            ans.addAll(list);
        }

        ans.sort(Comparator.comparing(a -> a));

        return ans.toArray(new String[0]);
    }

    public static List<String> maxComb(String[] orders, int k) {
        Map<String, Integer> cnt = new HashMap<>();

        for (String order : orders) {
            List<String> list = new ArrayList<>();
            if (order.length() >= k) {
                char[] arr = order.toCharArray();
                Arrays.sort(arr);
                combination(arr, k, 0, new StringBuilder(), list);
            }

            for (String s : list) {
                cnt.put(s, cnt.getOrDefault(s, 0) + 1);
            }
        }

        List<String> maxCombList = new ArrayList<>();

        int max = 0;
        for (int n : cnt.values()) {
            max = Math.max(max, n);
        }

        if (max > 1) {
            for (String key : cnt.keySet()) {
                if (cnt.get(key) == max) {
                    maxCombList.add(key);
                }
            }
        }

        return maxCombList;
    }

    public static void combination(char[] arr, int k, int depth, StringBuilder sb, List<String> result) {
        if (sb.length() == k) {
            result.add(sb.toString());
            return;
        }

        for (int i = depth; i < arr.length; i++) {
            sb.append(arr[i]);
            combination(arr, k, i + 1, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
