package level2;

public class 퍼즐게임챌린지 {

    public static void main(String[] args) {
        int[] diffs = {1, 99999, 100000, 99995};
        int[] times = {9999, 9001, 9999, 9001};
        long limit = 3456789012l;

        System.out.println(solution(diffs, times, limit));
    }

    public static int solution(int[] diffs, int[] times, long limit) {
        int level = 1;
        int lowLevel = 1;
        int highLevel = 1;

        boolean isOver = false;

        while (true) {
            long usage = calUsage(level, diffs, times);

            if (!isOver) {
                if (usage <= limit) {
                    isOver = true;
                    continue;
                }

                lowLevel = level;
                level *= 2;
                highLevel = level;
            } else {
                if (usage <= limit) {
                    if (highLevel - lowLevel <= 1) {
                        break;
                    }

                    highLevel = level;

                    level -= Math.round(((float) highLevel - lowLevel) / 2);
                } else {
                    lowLevel = level;

                    level += Math.round(((float) highLevel - lowLevel) / 2);
                }
            }
        }

        return level;
    }

    public static long calUsage(int level, int[] diffs, int[] times) {
        long usage = 0;

        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] > level) {
                usage += (times[i - 1] + times[i]) * (diffs[i] - level) + times[i];
            } else {
                usage += times[i];
            }
        }

        return usage;
    }
}
