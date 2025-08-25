package level2;

public class 큰수만들기 {
  public static void main(String[] args) {
    String number = "4177252841";
    int k = 4;
    System.out.println(solution(number, k));
  }

  public static String solution(String number, int k) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < number.length(); i++) {
      char c = number.charAt(i);

      while (k > 0 && sb.length() > 0 && sb.charAt(sb.length() - 1) < c) {
        sb.deleteCharAt(sb.length() - 1);
        k--;
      }
      sb.append(c);
    }

    if (k > 0) {
      sb.setLength(sb.length() - k);
    }

    return sb.toString();
  }
}
