package level2;

import java.util.HashMap;
import java.util.Map;

public class 의상 {
  public static void main(String[] args) {

  }

  public static int solution(String[][] clothes) {
    Map<String, Integer> m = new HashMap<>();
    for (String[] c : clothes) {
      m.put(c[1], m.getOrDefault(c[1], 0) + 1);
    }

    int answer = 1;
    for (int v : m.values()) {
      answer *= v + 1;
    }

    return answer - 1;
  }
}
