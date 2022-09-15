package practicum.sprint_7;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;

public class Journey {// чуть-чуть не проходит по времени


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int[] first = getInputArray(reader);
            int[] second = Arrays.stream(first).distinct().sorted().toArray();
            int[][] matrix = new int[first.length + 1][second.length + 1];
            fillMatrix(matrix, first, second);
            System.out.println(matrix[first.length][second.length]);
            writeResult(first, second, matrix, writer);
        }
    }

    private static void writeResult(int[] first, int[] second, int[][] matrix, BufferedWriter writer) throws IOException {
        int firstIndex = first.length;
        int secondIndex = second.length;
        ArrayList<Integer> firstLineIndexes = new ArrayList<>();
        while (firstIndex != 0 && secondIndex != 0) {
            if (first[firstIndex - 1] == second[secondIndex - 1]) {
                firstLineIndexes.add(firstIndex);
                firstIndex = firstIndex - 1;
                secondIndex = secondIndex - 1;
            } else if (matrix[firstIndex][secondIndex] == matrix[firstIndex][secondIndex - 1]) {
                secondIndex = secondIndex - 1;
            } else {
                firstIndex = firstIndex - 1;
            }
        }
        printReversedList(firstLineIndexes, writer);
        writer.write("\n");
    }


    private static <T> void printReversedList(ArrayList<Integer> list, Writer writer) throws IOException {
        for (int i = list.size() - 1; i >= 0; i--) {
            writer.write(list.get(i) + " ");
        }
    }

    private static void fillMatrix(int[][] matrix, int[] first, int[] second) {
        for (int i = 1; i <= first.length; i++) {
            for (int j = 1; j <= second.length; j++) {
                int firstLetter = first[i - 1];
                int secondLetter = second[j - 1];
                if (Objects.equals(firstLetter, secondLetter)) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else { // todo
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                }
            }
        }
    }


    private static int[] getInputArray(final BufferedReader reader) throws IOException {
        int count = Integer.parseInt(reader.readLine());
        final StringTokenizer splitter = new StringTokenizer(reader.readLine(), " ");
        final int[] list = new int[count];

        for (int i = 0; i < count; i++) {
            list[i] = Integer.parseInt(splitter.nextToken());
        }
        return list;
    }
}
