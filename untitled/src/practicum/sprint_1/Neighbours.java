package practicum.sprint_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;
import java.io.IOException;

public class Neighbours {

    private static List<Integer> getNeighbours(List<List<Integer>> matrix, int row, int col) {
        List<Integer> result = new ArrayList<>();
        List<Integer> currentCol = matrix.get(row);
        if (col != 0) {
            result.add(currentCol.get(col - 1));
        }

        if (col != currentCol.size() - 1) {
            result.add(currentCol.get(col + 1));
        }

        if (row != 0) {
            result.add(matrix.get(row - 1).get(col));
        }

        if (row != matrix.size() - 1) {
            result.add(matrix.get(row + 1).get(col));
        }
        return result.stream().sorted().collect(Collectors.toList());
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int rowsCount = readInt(reader);
            int colsCount = readInt(reader);
            List<List<Integer>> matrix = readMatrix(reader, rowsCount);
            int rowId = readInt(reader);
            int colId = readInt(reader);
            List<Integer> neighbours = getNeighbours(matrix, rowId, colId);
            for (int element : neighbours) {
                System.out.print(element + " ");
            }
        }
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }

    private static List<List<Integer>> readMatrix(BufferedReader reader, int rowsCount) throws IOException {
        List<List<Integer>> matrix = new ArrayList<>(rowsCount);
        for (int i = 0; i < rowsCount; i++) {
            matrix.add(readList(reader));
        }
        return matrix;
    }
}
