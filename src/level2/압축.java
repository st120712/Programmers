package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 압축 {

  // 1. [11, 1, 27, 15] [K,A,KA,O]
  public static void main(String[] args) {
    String msg = "TOBEORNOTTOBEORTOBEORNOT";
    System.out.println(Arrays.toString(solution(msg)));
  }

  static Map<String, Integer> dict = new HashMap<>();

  static int[] solution(String msg) {
    for (int i = 1; i <= 26; i++) {
      char c = (char) ('A' + i - 1);
      dict.put(c + "", i);
    }
    

    StringBuilder sb = new StringBuilder();
    int idx = 0;

    ArrayList<Integer> ans = new ArrayList<>();
    while (idx <= msg.length()) {
      if (idx == msg.length()) {
        ans.add(dict.get(sb.toString()));
        break;
      }

      sb.append(msg.charAt(idx++));

      if (!dict.containsKey(sb.toString())) {
        dict.put(sb.toString(), dict.size() + 1);
        sb.deleteCharAt(sb.length() - 1);
        ans.add(dict.get(sb.toString()));
        sb = new StringBuilder();
        idx--;
      }
    }

    int[] anss = new int[ans.size()];
    for (int i = 0; i < ans.size(); i++) {
      anss[i] = ans.get(i);
    }

    return anss;
  }
}
