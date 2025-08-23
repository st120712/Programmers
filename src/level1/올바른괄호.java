package level1;

import java.util.Stack;

public class 올바른괄호 {
  public static void main(String[] args) {

  }

  public static boolean solution(String s) {
    Stack<Character> stack = new Stack<>();

    for (char c : s.toCharArray()) {
      if (!stack.isEmpty() && stack.peek().equals('(') && c == ')') {
        stack.pop();
        continue;
      }
      stack.push(c);
    }

    return stack.isEmpty();
  }
}
