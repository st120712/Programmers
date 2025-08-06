package level2;

import java.util.*;

public class 도넛과막대그래프 {

    public static void main(String[] args) {
        int[][] edges = {{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}};

        System.out.println(Arrays.toString(solution(edges)));
    }

    public static int[] solution(int[][] edges) {

        int[] answer = new int[4];
        Map<Integer, List<Integer>> adjEdges = toAdj(edges);

        int extraNode = findExtraNode(edges);

        if (extraNode == -1 || !adjEdges.containsKey(extraNode)) {
            return new int[]{0, 0, 0, 0};
        }

        List<int[][]> graphs = findGraphs(extraNode, adjEdges);

        for (int i = 0; i < graphs.size(); i++) {
            int kind = getKindOfGraph(graphs.get(i));
            answer[kind + 1]++;
        }
        answer[0] = extraNode;
        return answer;
    }

    public static int getKindOfGraph(int[][] graph) {
        if (graph.length == 0) {
            return 1;
        }

        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, Integer> outDegree = new HashMap<>();
        Set<Integer> nodes = new HashSet<>();

        for (int[] edge : graph) {
            int from = edge[0];
            int to = edge[1];
            outDegree.put(from, outDegree.getOrDefault(from, 0) + 1);
            inDegree.put(to, inDegree.getOrDefault(to, 0) + 1);
            nodes.add(from);
            nodes.add(to);
        }

        int degreeOneCount = 0;
        for (int node : nodes) {
            int in = inDegree.getOrDefault(node, 0);
            int out = outDegree.getOrDefault(node, 0);

            if (in == 2 && out == 2) {
                return 2; // 8자형
            }
            if (in == 0 && out == 1) {
                degreeOneCount++; // 막대형 시작점 후보
            }
        }
        if (degreeOneCount == 1) {
            return 1;
        }
        return 0;
    }

    public static List<int[][]> findGraphs(int extraNode, Map<Integer, List<Integer>> adjEdges) {
        List<int[][]> graphs = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        for (int node : adjEdges.get(extraNode)) {
            if (!visited.contains(node)) {
                List<int[]> graph = new ArrayList<>();
                dfs(node, visited, graph, adjEdges);
                graphs.add(graph.toArray(new int[0][]));
            }
        }

        return graphs;
    }

    public static void dfs(int node, Set<Integer> visit, List<int[]> graph, Map<Integer, List<Integer>> adjEdges) {
        if (visit.contains(node)) {
            return;
        }

        visit.add(node);

        for (int nextNode : adjEdges.getOrDefault(node, List.of())) {
            graph.add(new int[]{node, nextNode});
            dfs(nextNode, visit, graph, adjEdges);
        }
    }

    public static int findExtraNode(int[][] edges) {
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, Integer> outDegree = new HashMap<>();

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            outDegree.put(from, outDegree.getOrDefault(from, 0) + 1);
            inDegree.put(to, inDegree.getOrDefault(to, 0) + 1);
        }

        for (int node : outDegree.keySet()) {
            if (!inDegree.containsKey(node) && outDegree.get(node) >= 2) {
                return node;
            }
        }

        return -1;
    }

    public static Map<Integer, List<Integer>> toAdj(int[][] edges) {
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for (int[] edge : edges) {
            adj.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
        }

        return adj;
    }
}
