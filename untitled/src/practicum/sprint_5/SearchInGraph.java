package practicum.sprint_5;

import java.util.Stack;

public class SearchInGraph {

    public static int treeRecursiveSolution(Node head) {
        int left = Integer.MIN_VALUE;
        if (head.left != null) {
            left = treeSolution(head.left);
        }
        int right = Integer.MIN_VALUE;
        if (head.right != null) {
            right = treeSolution(head.right);
        }
        return Math.max(Math.max(head.value, left), right);
    }

    public static int treeSolution(Node head) {
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        int result = Integer.MIN_VALUE;
        while (!stack.isEmpty()) {
            Node node = stack.pop();

            if (node.value > result) {
                result = node.value;
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return result;
    }

    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(2);
        node4.left = node3;
        int solution = treeSolution(node4);
        System.out.println(solution);
        System.out.println(solution == 3);
    }
}
