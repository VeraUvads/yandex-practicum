package practicum.sprint_7;

import java.io.*;
import java.util.StringTokenizer;

public class Journey {

    private static int[][] matrix;
    private static int[] list;
    private static int maxLine = 0;
    private static int maxResult = 0;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            list = getInputArray(reader);
            matrix = new int[list.length + 1][list.length + 1];
            for (int i = 1; i <= list.length; i++) {
                int maxVal = 0;
                for (int j = 1; j <= list.length; j++) {
//                    int previousMaxSubCount = matrix[i][j - 1];
//                    int previous = list[lastIncrease];
//                    int current = list[j];
//                    if (previous < current) {
//                        matrix[i][j] = previousMaxSubCount + 1;
//                        lastIncrease = j;
//                    } else {
//                        matrix[i][j] = previousMaxSubCount;
//                    }
                    matrix[i][j] = matrix[i - 1][j]; // почему
                    if (list[i - 1] == list[j - 1]) {
                        matrix[i][j] = Math.max(matrix[i][j], maxVal + 1);
                    }
                    if (list[j - 1] < list[i - 1]) {
                        maxVal = Math.max(maxVal, matrix[i - 1][j]);
                    }
                }
                int resultForLine = matrix[i][matrix[i].length - 1];
                if (resultForLine > maxResult) {
                    maxResult = resultForLine;
                    maxLine = i;
                }
            }
            writer.write(maxResult + "\n");
//            int previous = Integer.MIN_VALUE;
//            for (int i = maxLine; i < list.length; i++) {
//                if (previous < list[i]) {
//                    writer.write(i + 1 + " ");
//                    previous = list[i];
//                }
//            }
            for (int i = 1; i <= list.length; i++) {
                if (matrix[i][i] > matrix[i-1][i-1]) {
                    writer.write(i+ " ");
                }
            }
        }
    }

    private static int[] getInputArray(final BufferedReader reader) throws IOException {
        final int n = Integer.parseInt(reader.readLine());
        final StringTokenizer splitter = new StringTokenizer(reader.readLine(), " ");
        final int[] list = new int[n];

        for (int i = 0; i < n; i++) {
            list[i] = Integer.parseInt(splitter.nextToken());
        }
        return list;
    }
}
