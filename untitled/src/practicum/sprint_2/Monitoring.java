package practicum.sprint_2;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Monitoring {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int rowsCount = readInt(reader);
            final int colsCount = readInt(reader);
            LinkedList<LinkedList<Integer>> matrix = readMatrix(reader, rowsCount);
            LinkedList<LinkedList<Integer>> result = transposeMatrix(matrix, rowsCount, colsCount);
            for (LinkedList<Integer> list : result) {
                printList(list);
                System.out.println();
            }
        }
    }

    private static LinkedList<LinkedList<Integer>> transposeMatrix(LinkedList<LinkedList<Integer>> matrix, int rowsCount, int colsCount) {
        for (int rowIndex = 0; rowIndex < rowsCount; rowIndex++) {
            for (int columnIndex = rowIndex; columnIndex < colsCount; columnIndex++) {
                if (columnIndex < rowsCount) {
                    int temp = matrix.get(columnIndex).get(rowIndex);
                    matrix.get(columnIndex).set(rowIndex, matrix.get(rowIndex).get(columnIndex));
                    matrix.get(rowIndex).set(columnIndex, temp);
                }
            }
            if (rowIndex >= colsCount) {
                LinkedList<Integer> list = matrix.get(rowIndex);
                for (int row = 0; row < colsCount; row++) {
                    matrix.get(row).add(list.get(row));
                }
            }
        }
        for (int row = colsCount; row < rowsCount; row++) {
            matrix.removeLast();
        }
        for (int column = rowsCount; column < colsCount; column++) {
            matrix.add(new LinkedList<>());
            for (int row = 0; row < rowsCount; row++) {
                matrix.getLast().add(matrix.get(row).get(column));
            }
        }
        for (int column = rowsCount; column < colsCount; column++) {
            for (int row = 0; row < rowsCount; row++) {
                matrix.get(row).removeLast();
            }
        }
        return matrix;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static LinkedList<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toCollection(LinkedList::new));
    }

    private static LinkedList<LinkedList<Integer>> readMatrix(BufferedReader reader, int rowsCount) throws IOException {
        LinkedList<LinkedList<Integer>> matrix = new LinkedList<>();
        for (int i = 0; i < rowsCount; i++) {
            matrix.add(readList(reader));
        }
        return matrix;
    }

    private static <T> void printList(List<T> list) {
        list.forEach(elem -> System.out.print(elem + " "));
    }
}
