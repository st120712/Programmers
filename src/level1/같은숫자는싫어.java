package level1;

import java.util.Stack;

public class 같은숫자는싫어 {
  public static void main(String[] args) {

  }
  
  public static int[] solution(int[] arr) {
    
    Stack<Integer> stack = new Stack<>();
    for (int n : arr) {
      if (!stack.isEmpty() && stack.peek() == n) {
        continue;
      }
      stack.push(n);
    }

    int[] ans = new int[stack.size()];
    int size = 0;
    while (!stack.isEmpty()) {
      ans[ans.length - size++ - 1] = stack.pop();
    }

    return ans;
  }
}
