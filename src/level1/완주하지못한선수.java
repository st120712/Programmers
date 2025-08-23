package level1;

import java.util.HashMap;
import java.util.Map;

public class 완주하지못한선수 {
  public static void main(String[] args) {

  }

  public static String solution(String[] participant, String[] completion) {
    Map<String, Boolean> map = new HashMap<>();

    for (String p : participant) {
      map.put(p, false);
    }

    for (String c : completion) {
      map.put(c, true);
    }

    for (String k : map.keySet()) {
      if (!map.get(k)) {
        return k;
      }
    }

    return "";
  }
}
