import java.util.Arrays;

public class 비밀코드해독 {
  public static void main(String[] args) {
    int n = 10;
    int[][] q = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 3, 7, 8, 9, 10 }, { 2, 5, 7, 9, 10 }, { 3, 4, 5, 6, 7 } };
    int[] ans = { 2, 3, 4, 3, 3 };

    System.out.println(solution(n, q, ans));
  }

  public static int solution(int n, int[][] q, int[] ans) {
    int answer = 0;

    return answer;
  }

  public IArrayList2D choose(int k, IArrayList list) {
    IArrayList2D result = new IArrayList2D();
    int[] temp = new int[k];
    backTracking(0, 0, k, list, temp, result);

    return result;
  }

  public void backTracking(int depth, int start, int k, IArrayList list, int[] temp, IArrayList2D result) {
    if (depth == k) {
      result.add(Arrays.copyOf(temp, k));
      return;
    }

    for (int i = start; i < list.size(); i++) {
      temp[depth] = list.get(i);
      backTracking(depth + 1, i + 1, k, list, temp, result);
    }
  }

  public static class IArrayList {
    int[] values;
    int size;

    public IArrayList() {
      values = new int[10];
      size = 0;
    }

    public void add(int n) {
      if (values.length == size) {
        grow();
      }
      values[size++] = n;
    }

    public int get(int index) {
      if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException("invalid index: " + index);
      }
      return values[index];
    }

    public int size() {
      return size;
    }

    public void remove(int index) {
      if (index < 0 || index > size) {
        throw new IndexOutOfBoundsException("invalid index: " + index);
      }

      for (int i = index; i < size - 1; i++) {
        values[i] = values[i + 1];
      }
      size--;
    }

    public int[] toArray() {
      return Arrays.copyOf(values, size);
    }

    private void grow() {
      int newCapacity = values.length * 2;
      values = Arrays.copyOf(values, newCapacity);
    }
  }

  public static class IArrayList2D {
    private int[][] values;
    private int size;

    public IArrayList2D() {
      values = new int[10][];
      size = 0;
    }

    public void add(int[] arr) {
      if (size == values.length) {
        grow();
      }
      values[size++] = arr;
    }

    public void remove(int index) {
      if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException("invalid index: " + index);
      }
      for (int i = index; i < size; i++) {
        values[i] = values[i + 1];
      }
      size--;
    }

    public int[] get(int index) {
      if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException("invalid index: " + index);
      }
      return values[index];
    }

    public int size() {
      return size;
    }

    private void grow() {
      int[][] temp = new int[values.length * 2][];
      System.arraycopy(values, 0, temp, 0, values.length);
      values = temp;
    }
  }
}
