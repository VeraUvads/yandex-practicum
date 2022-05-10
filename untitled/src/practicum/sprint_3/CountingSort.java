package practicum.sprint_3;

import java.io.*;
import java.util.StringTokenizer;

public class CountingSort {


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int[] list = getInputArray(reader);
            int[] result = sort(list);
            for (int i : result) {
                writer.write(i + " ");
            }
        }

    }

    private static int[] sort(int[] list) {
        int[] colors = new int[3];
        for (int i = 0; i < list.length; i++) {
            colors[list[i]]++;
        }
        int[] result = new int[list.length];
        int index = 0;

        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < colors[i]; j++) {
                result[index] = i;
                index++;
            }
        }
        return result;
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
