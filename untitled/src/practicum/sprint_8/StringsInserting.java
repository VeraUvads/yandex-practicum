package practicum.sprint_8;


import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class StringsInserting {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String defaultLine = reader.readLine();
            ArrayList<Node> array = getNodeArray(reader);
            int start = 0;
            for (Node pair : array) {
                int index = pair.getFirst();
                String line = pair.getSecond();
                writer.write(defaultLine.substring(start, index));
                writer.write(line);
                start = index;
            }
            writer.write(defaultLine.substring(start));
        }
    }

    private static ArrayList<Node> getNodeArray(BufferedReader reader) throws IOException {
        final int n = Integer.parseInt(reader.readLine());
        ArrayList<Node> array = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            final StringTokenizer splitter = new StringTokenizer(reader.readLine(), " ");
            final String line = splitter.nextToken();
            final int index = Integer.parseInt(splitter.nextToken());
            array.add(new Node(index, line));
        }
        array.sort(Comparator.comparingInt(Node::getFirst));
        return array;
    }


    private static class Node {
        public int first;
        public String second;

        public Node(int first, String second) {
            this.first = first;
            this.second = second;
        }

        public int getFirst() {
            return first;
        }

        public String getSecond() {
            return second;
        }
    }
}
