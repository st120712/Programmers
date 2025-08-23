package level1;

import java.util.HashSet;
import java.util.Set;

public class 폰켓못 {
  public static void main(String[] args) {
    int[] nums = { 3, 1, 2, 3 };
    System.out.println(solution(nums));
  }
  
  public static int solution(int[] nums) {
    int N = nums.length;
    int G = N / 2;

    Set<Integer> set = new HashSet<>();
    for (int n : nums) {
      set.add(n);
    }

    if (set.size() >= G) {
      return G;
    } else {
      return set.size();
    }
  }
}
