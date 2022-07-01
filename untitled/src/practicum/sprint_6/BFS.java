package practicum.sprint_6;

import java.io.*;
import java.util.*;

public class BFS {


    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final String[] line = reader.readLine().split(" ");
            final int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);
            Map<Integer, ArrayList<Integer>> map = new HashMap<>();
            String[] colors = new String[n + 1];
            fillMap(map, reader, m, n);
            int startFrom = Integer.parseInt(reader.readLine());
            dfsGraph(map, colors, startFrom, writer);
        }
    }

    private static void dfsGraph(Map<Integer, ArrayList<Integer>> map, String[] colors, int startFrom, BufferedWriter writer) throws IOException {
        Queue<Integer> stack = new LinkedList<>();
        stack.add(startFrom);
        while (!stack.isEmpty()) {
            int vertex = stack.poll();
            if (colors[vertex] == null) {
                ArrayList<Integer> list = map.get(vertex);
                if (list != null) {
                    for (Integer integer : list) {
                        if (colors[integer] == null) {
                            stack.add(integer);
                        }
                    }
                }
                writer.write(vertex + " ");
                colors[vertex] = "grey";
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

    private static void fillMap(Map<Integer, ArrayList<Integer>> map, BufferedReader reader, int m, int n) throws IOException {
        for (int i = 0; i < m; i++) {
            final String[] line = reader.readLine().split(" ");
            final int from = Integer.parseInt(line[0]);
            final int to = Integer.parseInt(line[1]);
            if (map.containsKey(from)) {
                map.get(from).add(to);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(to);
                map.put(from, list);
            }
            if (map.containsKey(to)) {
                map.get(to).add(from);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(from);
                map.put(to, list);
            }
        }
        for (int i = 0; i <= n; i++) {
            if (map.containsKey(i)) {
                Collections.sort(map.get(i));
            }
        }
    }
}
