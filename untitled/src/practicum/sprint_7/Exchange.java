package practicum.sprint_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Exchange {

    private static int[] list;
    private static int profit = 0;

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            list = getInputArray(reader);
            countResult();
            System.out.println(profit);
        }
    }

    private static void countResult() {
        boolean hasStock = false;
        for (int i = 1; i < list.length; i++) {
            if (!hasStock && list[i - 1] < list[i]) {
                profit += list[i] - list[i - 1];
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
