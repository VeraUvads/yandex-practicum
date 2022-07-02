package practicum.sprint_6;

import java.io.*;

public class Attractions {
    //(not finished)
    private static int[] distances;
    //    private static int[] previous;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final String[] line = reader.readLine().split(" ");
            final int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);
            int[][] matrix = new int[n][n];
            distances = new int[n];
            visited = new boolean[n];
            fillMatrix(matrix, reader, m);

            // может быть хранить также и мапу, и идти по ней
            for (int[] ints : matrix) {
                printList(ints, writer);
            }
        }
    }

    private static <T> void printList(int[] list, Writer writer) throws IOException {
        for (int j : list) {
            writer.write(j + " ");
        }
        writer.write("\n");
    }

    private static void fillMatrix(int[][] matrix, BufferedReader reader, int m) throws IOException {
        for (int i = 0; i < m; i++) {
            final String[] line = reader.readLine().split(" ");
            final int from = Integer.parseInt(line[0]) - 1;
            final int to = Integer.parseInt(line[1]) - 1;
            final int weight = Integer.parseInt(line[2]);
            matrix[from][to] = weight;
            matrix[to][from] = weight;
        }
    }

    private static void relax(int[][] matrix, int u, int v) {
        if (distances[v] > distances[u] + matrix[u][v]) {
            distances[v] = distances[u] + matrix[u][v];
        }
    }

}
