package practice;

import java.util.Arrays;

public class 연습장 {

    public static void main(String[] args) {
        int[] nodes = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(Arrays.toString(solution(nodes)));
    }

    public static String[] solution(int[] nodes) {
        String[] ans = new String[3];
        ans[0] = preorder(nodes, 0).trim();
        ans[1] = inorder(nodes, 0).trim();
        ans[2] = postorder(nodes, 0).trim();

        return ans;
    }

    public static String preorder(int[] nodes, int idx) {
        if (idx >= nodes.length) {
            return "";
        }

        return "" + nodes[idx] + preorder(nodes, 2 * idx + 1) + preorder(nodes, 2 * idx + 2);
    }

    public static String inorder(int[] nodes, int idx) {
        if (idx >= nodes.length) {
            return "";
        }
        return "" + inorder(nodes, 2 * idx + 1) + nodes[idx] + inorder(nodes, 2 * idx + 2);
    }

    public static String postorder(int[] nodes, int idx) {
        if (idx >= nodes.length) {
            return "";
        }
        return "" + postorder(nodes, 2 * idx + 1) + postorder(nodes, 2 * idx + 2) + nodes[idx];
    }
}
