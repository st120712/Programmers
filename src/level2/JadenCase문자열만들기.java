package level2;

public class JadenCase문자열만들기 {

    public static void main(String[] args) {
        String s = "3people unFollowed me";

        System.out.println(solution(s));
    }

    public static String solution(String s) {
        s = s.toLowerCase();
        char[] sc = s.toCharArray();

        for (int i = 0; i < sc.length; i++) {
            if (Character.isAlphabetic(sc[0])) {
                sc[0] = Character.toUpperCase(sc[0]);
            }

            if (i != 0) {
                if (sc[i - 1] == ' ' && Character.isAlphabetic(sc[i])) {
                    sc[i] = Character.toUpperCase(sc[i]);
                }
            }
        }

        return String.valueOf(sc);
    }
}
