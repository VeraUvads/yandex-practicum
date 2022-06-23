package practicum.sprint_5.final_task;

/*
 *
 * -- ПРИНЦИП РАБОТЫ --
 *
 * Есть несколько кейсов при удалении узла в дереве:
 * 1) Узел является листом. Такой узел можно удалить без дополнительной работы.
 * 2) Узел имеет двух детей. Находим самый правый узел из левого дерева, либо самый левый из правого, и заменяем узлом.
 * 3) Узел имеет одного ребенка. Заменяем узел на ребенка.
 *
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * При удалении вершины дерево распадается. Чтобы этого избежать, надо соединить распавшиеся поддеревья новыми рёбрами.
 * Для того чтобы дерево осталось деревом поиска, на место удалённой вершины надо поставить какую-то другую вершину.
 * Это может быть либо  самый правый узел из левого дерева, либо самый левый из правого.
 * Если заменить подставить этот элемент вместо удаленного, то больше никаких  изменений производить будет не  нужно.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * В худшем случае нам нужно
 * 1) найти удаляемый элемент
 * 2) начиная от удаляемого  элемента продолжить поиск вглубь за элементом для замены.
 * Временная сложность составляет O(h) где h - глубина дерева.
 * В худшем случае если дерево несбалансированно то это будет O(n), в сбалансированном O(log(n))
 *
 *-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Память используется только для хранения дерева, поэтому пространственная сложность O(n)
 *
 * */



public class Solution {
    public static Node remove(Node root, int key) {
        if (root == null) return null;

        if (root.getValue() == key) {
            return removeItem(root);
        } else if (root.getValue() < key) {
            root.setRight(remove(root.getRight(), key));
        } else {
            root.setLeft(remove(root.getLeft(), key));
        }
        return root;
    }

    private static Node removeItem(Node root) {
        if (root.getRight() != null && root.getLeft() != null) {
            Node parent = getMinParent(root.getRight(), root);
            Node node;
            if (parent.getLeft() != null) {
                node = parent.getLeft();
                parent.setLeft(node.getRight());
            } else {
                node = parent.getRight();
                parent.setRight(node.getRight());
            }
            node.setLeft(parent.getLeft());
            node.setRight(parent.getRight());
            return node;
        } else if (root.getLeft() != null) {
            return root.getLeft();
        } else {
            return root.getRight();
        }
    }

    public static Node getMinParent(Node root, Node parent) {
        Node node;
        while (true) {
            node = root.getLeft();
            if (node != null) {
                parent = root;
                root = node;
            } else {
                break;
            }
        }
        return parent;
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;

        Node(Node left, Node right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private static void test() {
        Node node1 = new Node(null, null, 2);
        Node node2 = new Node(node1, null, 3);
        Node node3 = new Node(null, node2, 1);
        Node node4 = new Node(null, null, 6);
        Node node5 = new Node(node4, null, 8);
        Node node6 = new Node(node5, null, 10);
        Node node7 = new Node(node3, node6, 5);
        Node newHead = remove(node7, 10);
        System.out.println(newHead.getValue() == 5);
        System.out.println(newHead.getRight() == node5);
        System.out.println(newHead.getRight().getValue() == 8);
    }

    public static void main(String[] args) {
        test();
    }
}
