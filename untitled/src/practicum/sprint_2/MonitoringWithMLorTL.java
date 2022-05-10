package practicum.sprint_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MonitoringWithMLorTL {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int rowsCount = readInt(reader);
            final int colsCount = readInt(reader);
            final List<List<Integer>> matrix = readMatrix(reader, rowsCount);
            final List<List<Integer>> result = transposeMatrix(matrix, rowsCount, colsCount);
            for (final List<Integer> list : result) {
                printList(list);
                System.out.println();
            }
        }
    }

    private static List<List<Integer>> transposeMatrix(List<List<Integer>> matrix, int rowsCount, int colsCount) {
        final List<List<Integer>> result = new ArrayList<>(colsCount);
        for (int rowIndex = 0; rowIndex < colsCount; rowIndex++) {
            List<Integer> internalList = new ArrayList<>(rowsCount);
            for (int columnIndex = 0; columnIndex < rowsCount; columnIndex++) {
                internalList.add(matrix.get(columnIndex).get(rowIndex));
            }
            result.add(internalList);
        }
        return result;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());
    }

    private static List<List<Integer>> readMatrix(BufferedReader reader, int rowsCount) throws IOException {
        List<List<Integer>> matrix = new LinkedList<>();
        for (int i = 0; i < rowsCount; i++) {
            matrix.add(readList(reader));
        }
        return matrix;
    }

    private static <T> void printList(List<T> list) {
        list.forEach(elem -> System.out.print(elem + " "));
    }
}
