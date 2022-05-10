package practicum.sprint_2;

public class SolutionRemoveItem {
    public static NodeOneLinked<String> solution(NodeOneLinked<String> head, int idx) {
        if (idx == 0) {
            return getNodeByIndex(head, idx + 1);
        }
        getNodeByIndex(head, idx - 1).next = getNodeByIndex(head, idx + 1);
        return head;
    }

    private static NodeOneLinked<String> getNodeByIndex(NodeOneLinked<String> head, int idx) {
        for (int index = 0; index < idx; index++) {
            head = head.next;
            if (head == null) {
                return head;
            }
        }
        return head;
    }

    private static void test() {
        NodeOneLinked<String> node3 = new NodeOneLinked<>("node3", null);
        NodeOneLinked<String> node2 = new NodeOneLinked<>("node2", node3);
        NodeOneLinked<String> node1 = new NodeOneLinked<>("node1", node2);
        NodeOneLinked<String> node0 = new NodeOneLinked<>("node0", node1);
        NodeOneLinked<String> newHead = solution(node0, 3);
        while (newHead != null) {
            System.out.println(newHead.value);
            newHead = newHead.next;
        }
    }

    public static void main(String[] args) {
        test();
    }
}
