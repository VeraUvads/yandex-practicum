package practicum.sprint_6;

import java.io.*;

public class AdjacencyMatrix {

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final String[] line = reader.readLine().split(" ");
            final int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);
            int[][] matrix = new int[n][n];
            fillMatrix(matrix, reader, m);
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
            matrix[from][to] = 1;
        }
    }
}
