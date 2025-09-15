package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 파일정렬 {

    public static void main(String[] args) {
        String[] files = {"F-5", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
        System.out.println(Arrays.toString(solution(files)));
    }

    public static class XFile {

        String head, number, tail;

        public XFile(String filename) {
            splitFilename(filename);
        }

        private void splitFilename(String filename) {
            int numberStartIdx = 0;
            int numberEndIdx = filename.length();
            char[] chars = filename.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (numberStartIdx == 0 && Character.isDigit(chars[i])) {
                    numberStartIdx = i;
                }
                if (numberStartIdx != 0 && (!Character.isDigit(chars[i]))) {
                    numberEndIdx = i;
                    break;
                }
            }
            this.head = filename.substring(0, numberStartIdx);
            this.number = filename.substring(numberStartIdx, numberEndIdx);
            this.tail = filename.substring(numberEndIdx, chars.length);
        }

        @Override
        public String toString() {
            return head + number + tail;
        }
    }

    public static String[] solution(String[] files) {
        List<XFile> xfiles = new ArrayList<>();
        for (String file : files) {
            XFile xfile = new XFile(file);
            xfiles.add(xfile);
        }

        xfiles.sort((a, b) -> {
            if (a.head.toLowerCase().equals(b.head.toLowerCase())) {
                int numA = Integer.parseInt(a.number);
                int numB = Integer.parseInt(b.number);
                return Integer.compare(numA, numB);
            }
            return a.head.toLowerCase().compareTo(b.head.toLowerCase());
        });

        return xfiles.stream().map(XFile::toString).toArray(String[]::new);
    }
}
