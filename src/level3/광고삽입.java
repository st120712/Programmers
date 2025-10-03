package level3;

public class 광고삽입 {

    public static void main(String[] args) {
        String play_time = "02:03:55";
        String adv_time = "00:14:15";
        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};
        System.out.println(solution(play_time, adv_time, logs));
    }

    static String solution(String play_time, String adv_time, String[] logs) {
        int play = toSeconds(play_time);
        int adv = toSeconds(adv_time);

        if (adv >= play) {
            return "00:00:00";
        }

        long[] diff = new long[play + 2];
        for (String log : logs) {
            String[] parts = log.split("-");
            int s = toSeconds(parts[0]);
            int e = toSeconds(parts[1]);
            diff[s] += 1;
            diff[e] -= 1;
        }

        long[] viewers = new long[play + 1];
        long cur = 0;
        for (int t = 0; t <= play; t++) {
            cur += diff[t];
            viewers[t] = cur;
        }

        long[] watchPrefix = new long[play + 1];
        for (int t = 1; t <= play; t++) {
            watchPrefix[t] = watchPrefix[t - 1] + viewers[t - 1];
        }

        long bestSum = -1;
        int bestStrat = 0;
        for (int s = 0; s + adv <= play; s++) {
            int end = s + adv;
            long sum = watchPrefix[end] - watchPrefix[s];
            if (sum > bestSum) {
                bestSum = sum;
                bestStrat = s;
            }
        }

        return toHHMMSS(bestStrat);
    }

    static String toHHMMSS(int seconds) {
        int hh = seconds / 3600;
        seconds %= 3600;
        int mm = seconds / 60;
        seconds %= 60;
        int ss = seconds;

        String strHH = hh < 10 ? "0" + hh : "" + hh;
        String strMM = mm < 10 ? "0" + mm : "" + mm;
        String strSS = ss < 10 ? "0" + ss : "" + ss;

        return strHH + ":" + strMM + ":" + strSS;
    }

    static int toSeconds(String hhmmss) {
        String[] times = hhmmss.split(":");
        return Integer.parseInt(times[0]) * 3600 + Integer.parseInt(times[1]) * 60 + Integer.parseInt(times[2]);
    }
}
