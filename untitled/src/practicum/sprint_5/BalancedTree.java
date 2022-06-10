package practicum.sprint_5;

public class BalancedTree {

    public static boolean treeSolution(Node head) {
        if (head == null) {
            return true;
        }
        int leftDeep = getSubtreeDeep(head.left);
        int rightDeep = getSubtreeDeep(head.right);
        return Math.abs(leftDeep - rightDeep) <= 1 && treeSolution(head.left) && treeSolution(head.right);
    }

    private static int getSubtreeDeep(Node head) {
        if (head == null) {
            return 0;
        }
        int leftDeep = getSubtreeDeep(head.left);
        int rightDeep = getSubtreeDeep(head.right);
        return Math.max(leftDeep, rightDeep) + 1;
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

    private static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(10);
        Node node5 = new Node(2);
        node5.left = node3;
        node5.right = node4;
        System.out.println(treeSolution(node5) + " == true");

//        Node node0 = new Node(12);
//        Node node1 = new Node(4);
//        node1.left = node0;
//        Node node2 = new Node(8);
//        Node node3 = new Node(7);
//        node3.left = node1;
//        node3.right = node2;
//        Node node4 = new Node(2);
//        Node node5 = new Node(0);
//        node5.left = node4;
//        node5.right = node3;
//        System.out.println(treeSolution(node5) + " == false");
    }

    public static void main(String[] args) {
        test();
    }
}
