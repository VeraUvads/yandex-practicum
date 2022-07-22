package practicum.sprint_7;

import java.io.*;

public class FieldWithFlowers {

    private static int[][] matrix;
    private static int[][] result;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final String[] line = reader.readLine().split(" ");
            final int rowsCount = Integer.parseInt(line[0]);
            final int columnsCount = Integer.parseInt(line[1]);
            matrix = readMatrix(reader, rowsCount, columnsCount);
            result = new int[rowsCount][columnsCount];
            fillResult();
            System.out.println(result[0][columnsCount - 1]);
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

//    private static int getByIndex(int row, int column) { // recursive
//        if (row == matrix.length - 1 && column == 0) {
//            return matrix[row][column];
//        }
//        if (column < 0 || row > matrix.length - 1 ) {
//            return 0;
//        }
//        return Math.max(getByIndex(row + 1, column), getByIndex(row, column - 1)) + matrix[row][column];
//    }

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