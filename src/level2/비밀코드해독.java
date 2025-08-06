package level2;

public class 비밀코드해독 {

    static int answer = 0;

    public static void main(String[] args) {
        int n = 10;
        int[][] q = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {3, 7, 8, 9, 10}, {2, 5, 7, 9, 10}, {3, 4, 5, 6, 7}};
        int[] ans = {2, 3, 4, 3, 3};

        System.out.println(solution(n, q, ans));
    }

    public static int solution(int n, int[][] q, int[] ans) {
        backTracking(0, 1, n, 5, new IArrayList(), q, ans);

        System.out.println();
        return answer;
    }

    public static void backTracking(int depth, int start, int n, int k, IArrayList current, int[][] q, int[] ans) {
        if (depth == k) {
            answer++;
        }

        for (int i = start; i <= n; i++) {
            current.add(i);

            boolean valid = true;
            for (int j = 0; j < q.length; j++) {
                int count = countOverlap(current.toArray(), q[j]);
                int remainingCount = ans[j] - count;
                int remainingChoice = k - current.size();

                if (count > ans[j] || remainingCount > remainingChoice) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                backTracking(depth + 1, i + 1, n, k, current, q, ans);
            }

            current.remove(current.size() - 1);
        }
    }

    public static int countOverlap(int[] arr, int[] q) {
        int count = 0;
        for (int i : arr) {
            for (int j : q) {
                if (i == j) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    public static class IArrayList {

        private int[] values;
        private int size;

        public IArrayList() {
            values = new int[10];
            size = 0;
        }

        public void add(int n) {
            if (size == values.length) {
                grow();
            }
            values[size++] = n;
        }

        public void remove(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Invalid index: " + index);
            }
            for (int i = index; i < size - 1; i++) {
                values[i] = values[i + 1];
            }
            values[size - 1] = 0;
            size--;
        }

        public int get(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Invalid index: " + index);
            }
            return values[index];
        }

        public int size() {
            return size;
        }

        public int[] toArray() {
            return java.util.Arrays.copyOf(values, size);
        }

        public void print() {

            String str = "{ ";

            for (int i = 0; i < size; i++) {
                str += values[i] + " ";
            }

            str += "}";

            System.out.println(str);
        }

        private void grow() {
            int newCapacity = values.length * 2;
            values = java.util.Arrays.copyOf(values, newCapacity);
        }
    }
}
