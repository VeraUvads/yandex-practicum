package practicum.sprint_6;

import java.io.*;
import java.util.*;

public class MaxDistance {
    private static int[] distances;
    private static int[] previous;
    private static int max = 0;

    private static final int white = 0;
    private static final int grey = 1;
    private static final int black = 2;

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));) {
            final String[] line = reader.readLine().split(" ");
            final int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);
            Map<Integer, ArrayList<Integer>> map = new HashMap<>();
            int[] colors = new int[n + 1];
            distances = new int[n + 1];
            previous = new int[n + 1];
            fillMap(map, reader, m, n);
            int startFrom = Integer.parseInt(reader.readLine());
            dfsGraph(map, colors, startFrom);
        }
    }

    private static void dfsGraph(Map<Integer, ArrayList<Integer>> map, int[] colors, int startFrom) {
        Queue<Integer> stack = new LinkedList<>();
        stack.add(startFrom);
        distances[startFrom] = -1;
        previous[startFrom] = startFrom;
        while (!stack.isEmpty()) {
            int vertex = stack.poll();
            distances[vertex] = distances[previous[vertex]] + 1;
            if (distances[vertex] > max) {
                max = distances[vertex];
            }
            if (colors[vertex] == white || colors[vertex] == grey) {
                ArrayList<Integer> list = map.get(vertex);
                if (list != null) {
                    for (Integer integer : list) {
                        if (colors[integer] == white) {
                            stack.add(integer);
                            previous[integer] = vertex;
                            colors[integer] = grey;
                        }
                    }
                }
                colors[vertex] = black;
            }
        }
        System.out.println(max);
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
