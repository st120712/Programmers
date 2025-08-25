package level2;

import java.util.ArrayList;
import java.util.List;

public class 모음사전 {
  public static void main(String[] args) {
    solution("");
  }

  public static int solution(String word) {
    String[] gathers = { "A", "E", "I", "O", "U" };
    List<String> words = new ArrayList<>();
    int maxLen = 5;

    dfs("", maxLen, gathers, words);

    for (int i = 0; i < words.size(); i++) {
      if (word.equals(words.get(i))) {
        return i + 1;
      }
    }

    return -1;
  }

  public static void dfs(String w, int maxLen, String[] gathers, List<String> words) {
    if (w.length() > 0) {
      words.add(w);
    }

    if (w.length() == maxLen) {
      return;
    }

    for (String s : gathers) {
      w += s;
      dfs(w, maxLen, gathers, words);
      w = w.substring(0, w.length() - 1);
    }
  }
}
