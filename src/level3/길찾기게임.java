package level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 길찾기게임 {

    public static void main(String[] args) {
        int[][] nodeInfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
        System.out.println(Arrays.deepToString(solution(nodeInfo)));
    }

    static List<Node> nodes = new ArrayList<>();

    public static int[][] solution(int[][] nodeInfo) {
        Node root = buildTree(nodeInfo);
        System.out.println(root.toString());

        List<Integer> preorderList = new ArrayList<>();
        List<Integer> postorderList = new ArrayList<>();
        preorder(root, preorderList);
        postorder(root, postorderList);

        int[][] ans = new int[2][];
        ans[0] = preorderList.stream().mapToInt(Integer::intValue).toArray();
        ans[1] = postorderList.stream().mapToInt(Integer::intValue).toArray();

        return ans;
    }

    public static void preorder(Node root, List<Integer> list) {
        if (root == null || list.size() >= nodes.size()) {
            return;
        }

        list.add(root.num);
        preorder(root.left, list);
        preorder(root.right, list);
    }

    public static void postorder(Node root, List<Integer> list) {
        if (root == null || list.size() >= nodes.size()) {
            return;
        }

        postorder(root.left, list);
        postorder(root.right, list);
        list.add(root.num);
    }

    public static Node buildTree(int[][] nodeInfo) {
        int N = nodeInfo.length;
        for (int i = 0; i < N; i++) {
            nodes.add(new Node(nodeInfo[i][0], nodeInfo[i][1], i + 1));
        }

        nodes.sort((a, b) -> {
            if (a.y != b.y) {
                return Integer.compare(b.y, a.y);
            }
            return Integer.compare(a.x, b.x);
        });

        Node root = nodes.get(0);

        for (int i = 1; i < N; i++) {
            Node parent = root;
            while (true) {
                if (nodes.get(i).x < parent.x) {
                    if (parent.left == null) {
                        parent.left = nodes.get(i);
                        break;
                    } else {
                        parent = parent.left;
                    }
                } else {
                    if (parent.right == null) {
                        parent.right = nodes.get(i);
                        break;
                    } else {
                        parent = parent.right;
                    }
                }
            }
        }

        return root;
    }

    public static class Node {

        int x, y, num;
        Node left, right;

        public Node(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "{x : " + x + " y : " + y + " num : " + num + "}";
        }
    }
}
