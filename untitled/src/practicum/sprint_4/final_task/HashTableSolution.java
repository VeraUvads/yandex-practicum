package practicum.sprint_4.final_task;

import java.io.*;
import java.util.Objects;

public class HashTableSolution {
    private static final String GET = "get";
    private static final String PUT = "put";
    private static final String DELETE = "delete";

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final int commandsCount = readInt(reader);
            final HashTable hashTable = new HashTable(commandsCount, writer);
            for (int i = 0; i < commandsCount; i++) {
                final String[] line = reader.readLine().split(" ");
                final String command = line[0];
                final String key = line[1];
                if (Objects.equals(command, GET)) {
                    hashTable.get(key);
                }
                if (Objects.equals(command, PUT)) {
                    final String value = line[2];
                    hashTable.put(key, value);
                }
                if (Objects.equals(command, DELETE)) {
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
        private static final int PROBING = 11;
        private final Node[] list;
        private final int size;
        private final BufferedWriter writer;

        HashTable(int size, BufferedWriter writer) {
            this.writer = writer;
            list = new Node[size];
            this.size = size;
        }

        public void put(String key, String value) {
            int counter = 0;
            final Node nodeToPut = new Node(key, value);
            int index = getBucketIndex(key, counter);

            while (list[index] != null) {
                if (list[index].key.equals(key)) {
                    break;
                }
                counter++;
                index = getBucketIndex(key, counter);
            }
            list[index] = nodeToPut;
        }

        private int getBucketIndex(String key, int counter) {
            return (Long.valueOf(key).hashCode() % size + PROBING * counter) % size;
        }

        public int get(String key) {
            int counter = 0;
            int index = getBucketIndex(key, counter);
            while (list[index] != null) {
                Node item = list[index];
                if (item.key.equals(key)) {
                    write(item.value);
                    return index;
                }
                counter++;
                index = getBucketIndex(key, counter);
            }
            write(NONE);
            return -1;
        }

        private void write(String value) {
            try {
                writer.write(value + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void delete(String key) {
            int index = get(key);
            if (index != -1) {
                list[index].setDeleted();
            }
        }

        private static class Node {
            private String key;
            private String value;

            public Node(String key, String value) {
                this.key = key;
                this.value = value;
            }

            public void setDeleted() {
                this.key = DELETED;
                this.value = DELETED;
            }
        }
    }
}