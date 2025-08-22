package level1;

public class 문자열내p와y의개수 {

    public static void main(String[] args) {

    }

    public static boolean solution(String s) {
        s = s.toLowerCase();
        int countP = 0;
        int countY = 0;

        for (char c : s.toCharArray()) {
            if (c == 'p') {
                countP++;
            }

            if (c == 'y') {
                countY++;
            }
        }

        return countP == countY;
    }
}
