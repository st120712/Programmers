package level1;

public class 숫자문자열과영단어 {

    public static void main(String[] args) {
        String s = "one4seveneight";
        System.out.println(solution(s));
    }

    static int solution(String s) {
        try {
            int n = Integer.parseInt(s);
            return n;
        } catch (Exception e) {
            String[] numberForAlphabet = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

            for (int i = 0; i < numberForAlphabet.length; i++) {
                s = s.replaceAll(numberForAlphabet[i], i + "");
            }

            return Integer.parseInt(s);
        }
    }
}
