package practicum.sprint_6;

import java.io.*;
import java.util.*;

public class LinkedComponents {
//    (not finished)

    private static int currentColor = 1;
    private static int alreadyWatched = 2;
    //    private static int[] listForPrint;
    private static int index = 1;
    private static HashMap<Integer, ArrayList<Integer>> map;
    private static int[] colors;

    private static final int white = 0;

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final String[] line = reader.readLine().split(" ");
            final int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);
            map = new HashMap<>();
            colors = new int[n + 1];
//            listForPrint = new int[n + 1];
            fillMap(reader, m, n);

//            for (int i = 1; i < colors.length; i++) {
//                if (colors[i] == white) {
//                    dfsGraphRecursive(i, currentColor);
//                    currentColor++;
//                }
//            }
//            writer.write((currentColor-1) + "\n");
            dfsGraph();
            writer.write(currentColor + "\n");
//            ArrayList<Integer> chain = new ArrayList<>();
//            for (int i = 1; i <= n; i++) {
//                int vertex = listForPrint[i];
//                chain.add(listForPrint[i]);
//
//                if (i != n && colors[vertex] != colors[listForPrint[i + 1]]) {
//                    printList(chain, writer);
//                    chain.clear();
//                } else if (i == n) {
//                    printList(chain, writer);
//                }
//            }
            for (int i = 1; i <= currentColor; i++) {
                for (int vertex = 1; vertex < colors.length; vertex++) {
                    if (colors[vertex] == i) {
                        writer.write(vertex + " ");
                    }
                }
                writer.write("\n");
            }
        }
    }

    private static void dfsGraph() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (colors[vertex] == white) {
                if (map.containsKey(vertex)) {
                    ArrayList<Integer> list = map.get(vertex);
                    for (Integer integer : list) {
                        if (colors[integer] == white) {
                            stack.push(integer);
                        }
                    }
                }
//                listForPrint[index] = vertex;
//                index++;
                colors[vertex] = currentColor;
            }

            if (stack.isEmpty()) {
                for (int i = alreadyWatched; i < colors.length; i++) {
                    if (colors[i] == white) {
                        currentColor++;
                        stack.push(i);
                        alreadyWatched = i + 1;
                        break;
                    }
                }
            }
        }
    }

    private static void dfsGraphRecursive(int vertex, int color) {
        int label = colors[vertex];
        if (label == white) {
            colors[vertex] = color;
//            listForPrint[index] = vertex;
            index++;
            if (map.containsKey(vertex)) {
                ArrayList<Integer> list = map.get(vertex);
                for (Integer integer : list) {
                    if (colors[integer] == white) {
                        dfsGraphRecursive(integer, color);
                    }
                }
            }
        }
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
            if (map.containsKey(to)) {
                map.get(to).add(from);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(from);
                map.put(to, list);
            }
        }
    }

    private static void printList(ArrayList<Integer> list, Writer writer) throws IOException {
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            writer.write(list.get(i) + " ");
        }
        writer.write("\n");
    }

}