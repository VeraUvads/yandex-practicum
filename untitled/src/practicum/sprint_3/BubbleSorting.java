package practicum.sprint_3;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BubbleSorting {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int[] list = getInputArray(reader);
            sort(list, writer);
        }

    }

    private static void sort(int[] list, BufferedWriter writer) throws IOException {
        for (int i = 0; i < list.length - 1; i++) {
            boolean hasInsert = false;
            for (int j = 0; j < list.length - 1; j++) {
                int first = list[j];
                int second = list[j + 1];
                if (first > second) {
                    hasInsert = true;
                    list[j] = second;
                    list[j + 1] = first;
                }
            }
            if (!hasInsert) {
                if (i == 0) {
                    for (int element : list) {
                        System.out.print(element + " ");
                    }
                }
                break;
            }
            printList(list, writer);
            writer.write("\n");
        }
    }

    private static <T> void printList(int[] list, Writer writer) throws IOException {
        for (int elem : list) {
            writer.write(elem + " ");
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