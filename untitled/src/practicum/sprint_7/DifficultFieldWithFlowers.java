package practicum.sprint_7;

import java.io.*;
import java.util.Stack;

public class DifficultFieldWithFlowers {

    private static int[][] matrix;
    private static int[][] result;
    private static Stack<String> route;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final String[] line = reader.readLine().split(" ");
            final int rowsCount = Integer.parseInt(line[0]);
            final int columnsCount = Integer.parseInt(line[1]);
            matrix = readMatrix(reader, rowsCount, columnsCount);
            result = new int[rowsCount][columnsCount];
            route = new Stack<>();
            fillResult();
            writer.write(result[0][columnsCount - 1] + "\n");
            fillRoute();
            while (!route.isEmpty()) {
                writer.write(route.pop());
            }
        }
    }

    private static void fillRoute() {
        int row = 0;
        int column = result[row].length - 1;
        while (true) {
            int left = getByIndex2(column - 1, row);
            int down = getByIndex2(column, row + 1);
            if (down > left && down != -1) {
                row++;
                route.add("U");
            } else if (left != -1) {
                column--;
                route.add("R");
            } else {
                break;
            }
        }
    }

    private static void fillResult() {
        int rowStart = matrix.length - 1;
        int columnStart = 0;
        for (int row = rowStart; row >= 0; row--) {
            for (int column = columnStart; column < matrix[row].length; column++) {
                int left = getByIndex(column - 1, row);
                int down = getByIndex(column, row + 1);
                int max = Math.max(left, down) + matrix[row][column];
                int previous = result[row][column];
                result[row][column] = Math.max(max, previous);
            }
        }
    }

    private static int getByIndex(int column, int row) {
        if (row < 0 || column < 0 || row >= matrix.length || column >= matrix[row].length) {
            return 0;
        } else {
            return result[row][column];
        }
    }

    private static int getByIndex2(int column, int row) {
        if (row < 0 || column < 0 || row >= matrix.length || column >= matrix[row].length) {
            return -1;
        } else {
            return result[row][column];
        }
    }

    private static int[][] readMatrix(BufferedReader reader, int rowsCount, int columnsCount) throws IOException {
        int[][] matrix = new int[rowsCount][columnsCount];
        for (int i = 0; i < rowsCount; i++) {
            matrix[i] = readList(reader, columnsCount);
        }
        return matrix;
    }

    private static int[] readList(BufferedReader reader, int columnsCount) throws IOException {
        final char[] line = reader.readLine().toCharArray();
        final int[] list = new int[columnsCount];

        for (int i = 0; i < columnsCount; i++) {
            list[i] = Integer.parseInt(String.valueOf(line[i]));
        }
        return list;
    }


}
