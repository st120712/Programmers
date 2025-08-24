package level3;

import java.util.ArrayList;
import java.util.Arrays;

public class 이중우선순위큐 {
  public static void main(String[] args) {
    String[] operations = { "I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333" };
    System.out.println(Arrays.toString(solution(operations)));
  }

  public static int[] solution(String[] operations) {
    ArrayList<Integer> numbers = new ArrayList<>();

    for (String o : operations) {
      if (o.startsWith("I")) {
        int n = Integer.parseInt(o.split(" ")[1]);
        numbers.add(n);
        numbers.sort((a, b) -> a - b);
      }

      if (!numbers.isEmpty() && o.startsWith("D")) {
        int n = Integer.parseInt(o.split(" ")[1]);
        if (n == 1) {
          numbers.remove(numbers.size() - 1);
        } else {
          numbers.remove(0);
        }
      }
    }

    if (!numbers.isEmpty()) {
      return new int[] { numbers.get(numbers.size() - 1), numbers.get(0) };
    }

    return new int[2];
  }
}
