package level2;

public class 타겟넘버 {

    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;

        System.out.println(solution(numbers, target));

    }

    public static int solution(int[] numbers, int target) {
        int ans = dfs(0, 0, numbers, 0, target);

        return ans;
    }

    public static int dfs(int n, int depth, int[] numbers, int count, int target) {
        if (depth == numbers.length) {
            if (n == target) {
                return ++count;
            } else {
                return count;
            }
        }

        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                n += numbers[depth++];
                count = dfs(n, depth, numbers, count, target);
                n -= numbers[--depth];
            } else {
                n -= numbers[depth++];
                count = dfs(n, depth, numbers, count, target);
                n += numbers[--depth];
            }
        }
        return count;
    }
}
