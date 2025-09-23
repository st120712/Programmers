package test.test3;

import java.util.Stack;

public class Exam2 {

    public static void main(String[] args) {
        int n = 500000;
        int k = 2;
        System.out.println(solution(n, k));
    }

    static int solution(int n, int k) {
        String converted = convertN(n, k);
        String[] numbers = converted.split("0");

        int ans = 0;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i].equals("")) {
                continue;
            }
            if (isPrime(Long.parseLong(numbers[i]))) {
                ans++;
            }
        }

        return ans;
    }

    static String convertN(int n, int k) {
        Stack<Integer> stack = new Stack<>();
        while (n != 0) {
            stack.push(n % k);
            n /= k;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    static boolean isPrime(long number) {
        if (number <= 1) {
            return false;
        }
        if (number <= 3) {
            return true;
        }
        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }
        for (long i = 5; i <= number / i; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
