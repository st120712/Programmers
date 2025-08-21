package level0;

import java.io.OutputStreamWriter;
import java.util.Scanner;

public class 문자열출력하기 {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();

        OutputStreamWriter writer = new OutputStreamWriter(System.out);

        writer.write(a);
        writer.flush();
    }
}
