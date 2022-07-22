package practicum.sprint_7;

import java.io.*;
import java.util.StringTokenizer;

public class LeprechaunGold {
    private static int[][] matrix;
    private static int[] goldWeights;
    private static int capacity;

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final String[] line = reader.readLine().split(" ");
            final int count = Integer.parseInt(line[0]);
            capacity = Integer.parseInt(line[1]);
            goldWeights = getInputArray(reader, count);
            matrix = new int[count + 1][capacity + 1];
            fillMatrix();
            System.out.println(matrix[count][capacity]);
        }
    }

    private static void fillMatrix() {
        int rowStart = 1;
        int columnStart = 1;
        for (int row = rowStart; row < matrix.length; row++) {
            for (int column = columnStart; column < matrix[row].length; column++) {
                int costWithout = matrix[row - 1][column];
                int costWith = getCostWith(row, column);
                matrix[row][column] = Math.max(costWithout, costWith);
            }
        }
    }

    private static int getCostWith(int row, int column) {
        int currentColumn = column - goldWeights[row - 1];
        if (currentColumn < 0) {
            return 0;
        } else {
            int costBeforeWeGet = matrix[row - 1][currentColumn];
            return costBeforeWeGet + goldWeights[row-1];
        }
    }

    private static int[] getInputArray(final BufferedReader reader, int count) throws IOException {
        final StringTokenizer splitter = new StringTokenizer(reader.readLine(), " ");
        final int[] list = new int[count];

        for (int i = 0; i < count; i++) {
            list[i] = Integer.parseInt(splitter.nextToken());
        }
        return list;
    }
}
