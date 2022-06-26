package practicum.sprint_6;

import java.io.*;
import java.util.*;

public class TimeToOut {

    private static int[] colors;
    private static int[] timeIn;
    private static int[] timeOut;
    private static HashMap<Integer, ArrayList<Integer>> map;
    private static int time = -1;


    private static final int white = 0;
    private static final int grey = 1;
    private static final int black = 2;

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final String[] line = reader.readLine().split(" ");
            final int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);
            map = new HashMap<>();
            colors = new int[n + 1];
            timeIn = new int[n + 1];
            timeOut = new int[n + 1];
            fillMap(reader, m, n);
//            dfsGraphRecursive(1);
            dfsGraph();
            for (int i = 1; i <= n; i++) {
                writer.write(timeIn[i] + " " + timeOut[i] + "\n");
            }
        }
    }

    private static void dfsGraph() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        int time = 0;
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            int label = colors[vertex];
            if (label == white) {
                timeIn[vertex] = time;
                colors[vertex] = grey;
                stack.push(vertex);
                ArrayList<Integer> list = map.get(vertex);
                if (list != null) {
                    for (int i = list.size() - 1; i >= 0; i--) {
                        if (colors[list.get(i)] == white) {
                            stack.add(list.get(i));
                        }
                    }
                }
                time++;
            } else if (label == grey) {
                colors[vertex] = black;
                timeOut[vertex] = time;
                time++;
            }
        }
    }


    private static void dfsGraphRecursive(int vertex) {
        time++;
        int label = colors[vertex];
        if (label == white) {
            timeIn[vertex] = time;
            colors[vertex] = grey;
            ArrayList<Integer> list = map.get(vertex);
            if (list != null) {
                for (Integer integer : list) {
                    if (colors[integer] == white) {
                        dfsGraphRecursive(integer);
                    }
                }
            }
        } else if (label == grey) {
            colors[vertex] = black;
            timeOut[vertex] = time;
        }
        time++;
        timeOut[vertex] = time;
    }

    private static void fillMap(BufferedReader reader, int m, int n) throws IOException {
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
        }
        for (int i = 0; i <= n; i++) {
            if (map.containsKey(i)) {
                Collections.sort(map.get(i));
            }
        }
    }
}
