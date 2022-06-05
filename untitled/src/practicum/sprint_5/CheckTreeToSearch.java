package practicum.sprint_5;

import java.util.LinkedList;
import java.util.Queue;

public class CheckTreeToSearch {

    public static boolean treeSolutionWithQueue(Node head) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.left != null) {
                Node left = node.left;
                if (left.value >= node.value) {
                    return false;
                }
                if (left.left != null && left.left.value >= left.value) {
                    return false;
                }
                if (left.right != null && (left.right.value <= left.value || left.right.value >= node.value)) {
                    return false;
                }
                queue.add(left);
            }

            if (node.right != null) {
                Node right = node.right;
                if (right.value <= node.value) {
                    return false;
                }
                if (right.left != null && (right.left.value >= right.value || right.left.value <= node.value)) {
                    return false;
                }
                if (right.right != null && right.right.value <= right.value) {
                    return false;
                }
                queue.add(right);
            }
        }
        return true;
    }

    public static boolean treeSolution(Node head) {
        boolean leftResult = true;
        boolean rightResult = true;
        if (head.left != null) {
            if (head.left.value >= head.value) {
                return false;
            }
            leftResult = treeSolutionRecursive(head, head.left, true);
        }

        if (head.left != null) {
            if (head.right.value <= head.value) {
                return false;
            }
            rightResult = treeSolutionRecursive(head, head.right, false);
        }

        return leftResult && rightResult;
    }

    private static boolean treeSolutionRecursive(Node grandHead, Node head, boolean isLeft) {
        boolean leftResult = true;
        boolean rightResult = true;
        if (head.left != null) {
            Node left = head.left;
            leftResult = treeSolutionRecursive(grandHead, left, true);

            if (left.value >= head.value) {
                return false;
            }

            if (isLeft && left.value >= grandHead.value || !isLeft && left.value <= grandHead.value) {
                return false;
            }
        }
        if (head.right != null) {
            Node right = head.right;
            rightResult = treeSolutionRecursive(grandHead, right, false);

            if (right.value <= head.value) {
                return false;
            }

            if (isLeft && right.value >= grandHead.value || !isLeft && right.value <= grandHead.value) {
                return false;
            }
        }


        return leftResult && rightResult;
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

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }


    public static void main(String[] args) {
        test();
    }

    private static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(4);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(8);
        Node node5 = new Node(5, node3, node4);
        System.out.println(treeSolutionWithQueue(node5)); // true
        node2.value = 5;
        System.out.println(treeSolutionWithQueue(node5)); // false
    }
}
