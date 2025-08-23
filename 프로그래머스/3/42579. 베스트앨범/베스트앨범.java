import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public static int[] solution(String[] genres, int[] plays) {
    int n = genres.length;

    Map<String, List<int[]>> m = new HashMap<>();
    for (int i = 0; i < n; i++) {
      List<int[]> temp = m.getOrDefault(genres[i], new ArrayList<>());
      temp.add(new int[] { i, plays[i] });
      m.put(genres[i], temp);
    }
    for (List<int[]> v : m.values()) {
      v.sort((a, b) -> {
        if (a[1] != b[1]) {
          return b[1] - a[1];
        }
        return a[0] - b[0];
      });
    }

    Map<String, Integer> count = new HashMap<>();
    List<String> gList = new ArrayList<>();
    for (String k : m.keySet()) {
      count.put(k, 0);
      gList.add(k);
    }

    gList.sort(Comparator.comparingInt(a -> {
      int total = 0;
      for (int[] s : m.get(a)) {
        total += s[1];
      }
      return -total;
    }));

    int[] ans = new int[m.size() * 2];
    int size = 0;

    for (String g : gList) {
      List<int[]> s = m.get(g);
      ans[size++] = s.get(0)[0];
      if (s.size() > 1) {
        ans[size++] = s.get(1)[0];
      }
    }

    return Arrays.copyOf(ans, size);
  }
}