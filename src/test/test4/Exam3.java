package test.test4;

import java.util.PriorityQueue;

public class Exam3 {

    public static void main(String[] args) {
        int[] A = {5, 1, 3, 7};
        int[] B = {2, 2, 6, 8};
        System.out.println(solution(A, B));
    }

    static int solution(int[] A, int[] B) {
        PriorityQueue<Integer> pqA = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
        PriorityQueue<Integer> pqB = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
        for (int i = 0; i < A.length; i++) {
            pqA.offer(A[i]);
        }
        for (int i = 0; i < B.length; i++) {
            pqB.offer(B[i]);
        }

        int ans = 0;
        while (!pqB.isEmpty()) {
            int b = pqB.poll();
            if (pqA.peek() < b) {
                pqA.poll();
                ans++;
            }
        }

        return ans;
    }
}
