package practicum.sprint_6;

import java.io.*;
import java.util.*;

public class AdjacencyList {

    static Map<Integer, ArrayList<Integer>> result = new HashMap<>();


    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final String[] line = reader.readLine().split(" ");
            final int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);
            fillResult(reader, m);
            for (int i = 1; i <= n; i++) {
                if (result.containsKey(i)) {
                    ArrayList<Integer> entry = result.get(i);
                    writer.write(entry.size() + " ");
                    printList(entry, writer);
                    writer.write("\n");
                } else {
                    writer.write(0 + "\n");
                }
            }
        }
    }

    private static <T> void printList(ArrayList<T> list, Writer writer) {
        list.forEach(elem -> {
                    try {
                        writer.write(String.valueOf(elem));
                        writer.write(" ");
                    } catch (IOException e) {
                    }
                }
        );
    }

    private static void fillResult(BufferedReader reader, int m) throws IOException {
        for (int i = 0; i < m; i++) {
            final String[] line = reader.readLine().split(" ");
            final int from = Integer.parseInt(line[0]);
            final int to = Integer.parseInt(line[1]);
            if (result.containsKey(from)) {
                result.get(from).add(to);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(to);
                result.put(from, list);
            }
        }

    }


}
