package level1;

public class 문자열다루기기본 {

    public static void main(String[] args) {
        String s = "1234";
        System.out.println(solution(s));
    }

    public static boolean solution(String s) {
        try {
            if (s.length() != 4 && s.length() != 6) {
                throw new RuntimeException();
            }
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
