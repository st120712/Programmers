package level3;

public class 자물쇠와열쇠 {

    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        System.out.println(solution(key, lock));
    }

    static boolean solution(int[][] key, int[][] lock) {
        int ln = lock.length;

        for (int i = 0; i < 4; i++) {
            int[][] expandedKey = expandKey(ln, key);
            int en = expandedKey.length;

            int maxStart = en - ln;
            for (int sy = 0; sy <= maxStart; sy++) {
                for (int sx = 0; sx <= maxStart; sx++) {
                    if (fits(expandedKey, lock, sy, sx)) {
                        return true;
                    }
                }
            }

            key = rotateKey(key);
        }

        return false;
    }

    static boolean fits(int[][] expandedKey, int[][] lock, int sy, int sx) {
        int n = lock.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (lock[i][j] + expandedKey[i + sy][j + sx] != 1) {
                    return false;
                }
            }
        }

        return true;
    }

    static int[][] expandKey(int ln, int[][] key) {
        int n = key.length;
        int[][] arr = new int[2 * ln + n][2 * ln + n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[ln + i][ln + j] = key[i][j];
            }
        }

        return arr;
    }

    static int[][] rotateKey(int[][] key) {
        int n = key.length;
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[j][n - 1 - i] = key[i][j];
            }
        }

        return arr;
    }
}
