package practicum.sprint_7;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Backpack {  // not finished
    private static int[][] matrix;
    private static int[] weights;
    private static int[] values;
    private static int capacity;

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final String[] line = reader.readLine().split(" ");
            final int count = Integer.parseInt(line[0]);
            capacity = Integer.parseInt(line[1]);
            fill(reader, count);
            matrix = new int[count + 1][capacity + 1];
            fillMatrix();
            System.out.println(matrix[count][capacity]);
        }
    }


//    private static void writeResult(BufferedWriter writer) throws IOException {
//        int firstIndex = first.length;
//        int secondIndex = second.length;
//        ArrayList<Integer> firstLineIndexes = new ArrayList<>();
//        ArrayList<Integer> secondLineIndexes = new ArrayList<>();
//        while (firstIndex != 0 && secondIndex != 0) {
//            if (first[firstIndex - 1] == second[secondIndex - 1]) {
//                firstLineIndexes.add(firstIndex);
//                secondLineIndexes.add(secondIndex);
//                firstIndex = firstIndex - 1;
//                secondIndex = secondIndex - 1;
//            } else if (matrix[firstIndex][secondIndex] == matrix[firstIndex][secondIndex - 1]) {
//                secondIndex = secondIndex - 1;
//            } else {
//                firstIndex = firstIndex - 1;
//            }
//        }
//        printReversedList(firstLineIndexes, writer);
//        writer.write("\n");
//        printReversedList(secondLineIndexes, writer);
//    }

//    private static void writeResult(BufferedWriter writer) throws IOException { // todo not finished
//        int row = matrix.length - 1;
//        int column = matrix[0].length - 1;
//        while (true) {
//            int left = matrix[row][column - 1];
//            int up = matrix[row - 1][column];
//            int current = matrix[row][column];
//            if (up == current - 1) {
//                row = row - 1;
//            } else {
//                column = column + 1;
//            }
//            if (row == 0 && column == matrix[row].length - 1) {
//                break;
//            }
//        }
//    }

    private static <T> void printReversedList(ArrayList<Integer> list, Writer writer) throws IOException {
        for (int i = list.size() - 1; i >= 0; i--) {
            writer.write(list.get(i) + " ");
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
        int currentColumn = column - weights[row - 1];
        if (currentColumn < 0) {
            return 0;
        } else {
            int costBeforeWeGet = matrix[row - 1][currentColumn];
            return costBeforeWeGet + 1;
        }
    }

    private static void fill(final BufferedReader reader, int count) throws IOException {
        weights = new int[count];
        values = new int[count];
        for (int i = 0; i < count; i++) {
            String[] line = reader.readLine().split(" ");
            weights[i] = Integer.parseInt(line[0]);
            values[i] = Integer.parseInt(line[1]);
        }
    }

}
