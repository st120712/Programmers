package level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 여행경로 {

    public static void main(String[] args) {
        String[][] tickets = {
            {"EZE", "TIA"}, {"EZE", "HBA"}, {"AXA", "TIA"}, {"ICN", "AXA"}, {"ANU", "ICN"},
            {"ADL", "ANU"}, {"TIA", "AUA"}, {"ANU", "AUA"}, {"ADL", "EZE"}, {"ADL", "EZE"},
            {"EZE", "ADL"}, {"AXA", "EZE"}, {"AUA", "AXA"}, {"ICN", "AXA"}, {"AXA", "AUA"},
            {"AUA", "ADL"}, {"ANU", "EZE"}, {"TIA", "ADL"}, {"EZE", "ANU"}, {"AUA", "ANU"}};
        System.out.println(Arrays.toString(solution(tickets)));
    }

    public static String[] solution(String[][] tickets) {
        int n = tickets.length + 1;
        Map<String, List<Ticket>> m = new HashMap<>();
        for (String[] t : tickets) {
            String dep = t[0];
            String arr = t[1];
            Ticket ticket = new Ticket(arr);
            List<Ticket> list = m.getOrDefault(dep, new ArrayList<>());
            list.add(ticket);
            m.put(dep, list);
        }
        for (List<Ticket> v : m.values()) {
            v.sort(Comparator.comparing(a -> a.getArrival()));
        }

        List<String> channels = new ArrayList<>();
        channels.add("ICN");

        dfs(m, channels, n);

        return channels.toArray(new String[0]);
    }

    public static void dfs(Map<String, List<Ticket>> m, List<String> channel, int n) {
        if (channel.size() == n) {
            return;
        }

        String dep = channel.get(channel.size() - 1);

        for (Ticket ticket : m.getOrDefault(dep, new ArrayList<>())) {
            if (ticket.isUsed()) {
                continue;
            }

            String arr = ticket.getArrival();
            channel.add(arr);
            ticket.use();
            dfs(m, channel, n);
            if (channel.size() == n) {
                return;
            }
            channel.remove(channel.size() - 1);
            ticket.unUse();
        }
    }

    public static class Ticket {

        String arrival;
        boolean used = false;

        public Ticket(String arrival) {
            this.arrival = arrival;
        }

        public String getArrival() {
            return arrival;
        }

        public boolean isUsed() {
            return used;
        }

        public void use() {
            this.used = true;
        }

        public void unUse() {
            this.used = false;
        }

        @Override
        public String toString() {
            return String.format("{Arrival : %s, isUsed : %s}", arrival, used);
        }
    }
}
