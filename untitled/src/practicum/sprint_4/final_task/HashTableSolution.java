package practicum.sprint_4.final_task;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;

public class HashTableSolution {
    private static final String GET = "get";
    private static final String PUT = "put";
    private static final String DELETE = "delete";

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int commandsCount = readInt(reader);
            final HashTable hashTable = new HashTable(commandsCount / 2);
            for (int i = 0; i < commandsCount; i++) {
                final String[] line = reader.readLine().split(" ");
                final int key = Integer.parseInt(line[1]);
                if (Objects.equals(line[0], GET)) {
                    hashTable.get(key);
                }
                if (Objects.equals(line[0], PUT)) {
                    final int value = Integer.parseInt(line[2]);
                    hashTable.put(key, value);
                }
                if (Objects.equals(line[0], DELETE)) {
                    hashTable.delete(key);
                }
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    public static class HashTable {
        private static final String NONE = "None";
        private static final String DELETED = "deleted";
        private static final int PROBING_ONE = 11;
        private static final int PROBING_TWO = 31;
        private Node[] list;
        private int size;
        private int deletingMax;
        private int deletedCount = 0;

        HashTable(int size) {
            list = new Node[size];
            Arrays.fill(list, new Node());
            this.size = size;
            this.deletingMax = size / 2;
        }

        public void put(int key, int value) {
            int hashCode = hash(key);
            int counter = 1;
            while (!list[hashCode].isEmpty()) {
                if (list[hashCode].getKey() == key) {
                    break;
                }
                hashCode = resolve(hashCode, counter);
                counter++;
            }
            list[hashCode] = new Node(key, value);
        }

        private int resolve(int hashCode, int counter) {
            return (int) (hashCode + PROBING_ONE * counter + PROBING_TWO * Math.pow(counter, 2)) % size;
        }

        public int get(int key) {
            int hashCode = hash(key);
            int counter = 1;
            while (!list[hashCode].isNone()) {
                Node item = list[hashCode];
                if (item.isDeleted() || item.getKey() != key) {
                    hashCode = resolve(hashCode, counter);
                    counter++;
                } else if (item.getKey() == key) {
                    System.out.println(item.value);
                    return hashCode;
                }
            }
            System.out.println(NONE);
            return -1;
        }

        private int hash(int key) {
            return key % size;
        }

        public void delete(int key) {
            int hashCode = get(key);
            if (hashCode != -1) {
                deletedCount++;
                list[hashCode] = Node.DeletedNode();
            }
            if (deletedCount == deletingMax) {
                Node[] temp = new Node[size];
                for (int newIndex = 0, oldIndex = 0; oldIndex < size; oldIndex++) {
                    Node node = list[oldIndex];
                    if (node.isDeleted()) {
                        temp[newIndex] = node;
                        newIndex++;
                    }
                }
                list = temp;
            }
        }

        private static class Node {
            public String key;
            public String value;

            public Node(String key, String value) {
                this.key = key;
                this.value = value;
            }

            public Node(int key, int value) {
                this.key = String.valueOf(key);
                this.value = String.valueOf(value);
            }

            public Node() {
                this.key = NONE;
                this.value = NONE;
            }

            public static Node DeletedNode() {
                return new Node(DELETED, DELETED);
            }

            public boolean isEmpty() {
                return isDeleted() || isNone();
            }

            public boolean isNone() {
                return Objects.equals(key, NONE);
            }

            public boolean isDeleted() {
                return Objects.equals(key, DELETED);
            }

            public int getKey() {
                return Integer.parseInt(key);
            }

            public int getValue() {
                return Integer.parseInt(value);
            }
        }
    }
}
