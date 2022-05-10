package practicum.sprint_2;

import java.util.Objects;

public class SolutionFindElement {
    public static int solution(NodeOneLinked<String> head, String elem) {
        int index = 0;
        while (head != null) {
            if (Objects.equals(head.value, elem)) {
                return index;
            }
            head = head.next;
            index++;
        }
        return -1;
    }

    private static void test() {
        NodeOneLinked<String> node3 = new NodeOneLinked<>("node3", null);
        NodeOneLinked<String> node2 = new NodeOneLinked<>("node2", node3);
        NodeOneLinked<String> node1 = new NodeOneLinked<>("node1", node2);
        NodeOneLinked<String> node0 = new NodeOneLinked<>("node0", node1);
        int idx = solution(node0, "node");
        System.out.println(idx);
    }

    public static void main(String[] args) {
        test();
    }
}