package level2;

public class 조이스틱 {
  public static void main(String[] args) {
    String name = "JEROEN";
    System.out.println(solution(name));
  }

  public static int solution(String name) {
    int n = name.length();

    int vertical = 0;
    for (char c : name.toCharArray()) {
      int d = c - 'A';
      vertical += Math.min(d, 26 - d);
    }

    int horizontal = n - 1;
    for (int i = 0; i < n; i++) {
      int j = i + 1;
      while (j < n && name.charAt(j) == 'A') {
        j++;
      }
      horizontal = Math.min(horizontal, i * 2 + (n - j));
      horizontal = Math.min(horizontal, (n - j) * 2 + i);
    }

    return vertical + horizontal;
  }
}
