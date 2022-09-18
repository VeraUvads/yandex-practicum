package practicum.sprint_8;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ShiftSearch {

    private static ArrayList<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int[] list = getInputArray(reader);
            int[] template = getInputArray(reader);
            fillResult(list, template);
            for (int i : result) {
                writer.write(i + 1 + " ");
            }
        }
    }

    private static void fillResult(int[] list, int[] template) {
        if (template.length == 0) return;
        for (int i = 0; i < list.length - template.length + 1; i++) {
            int diff = list[i] - template[0];
            int startPosition = i;
            for (int j = 0; j < template.length; j++) {
                if (template[j] + diff != list[startPosition]) {
                    break;
                } else {
                    startPosition++;
                }
                if (j == template.length - 1) {
                    result.add(i);
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
