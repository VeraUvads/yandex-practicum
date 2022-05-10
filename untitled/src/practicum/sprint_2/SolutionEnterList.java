package practicum.sprint_2;

///*
//Comment it before submitting
class NodeOneLinked<V> {
    public V value;
    public NodeOneLinked<V> next;

    public NodeOneLinked(V value, NodeOneLinked<V> next) {
        this.value = value;
        this.next = next;
    }
}
//*/

public class SolutionEnterList {
    public static void solution(NodeOneLinked<String> head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

    private static void test() {
        NodeOneLinked<String> node3 = new NodeOneLinked<>("node3", null);
        NodeOneLinked<String> node2 = new NodeOneLinked<>("node2", node3);
        NodeOneLinked<String> node1 = new NodeOneLinked<>("node1", node2);
        NodeOneLinked<String> node0 = new NodeOneLinked<>("node0", node1);
        solution(node0);
        /*
        Output is:
        node0
        node1
        node2
        node3
        */
    }
//
    public static void main(String[] args) {
        test();
    }
}
