package practicum.sprint_1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClosestZero {
    private static final int INF = 99999999;

    private static List<Integer> getDistanceList(List<Integer> data) {
        int zeroIndex = -INF;
        int lastCalculatedIndex = -1;
        for (int i = 0; i < data.size(); i++) {
            final boolean isLastElement = i == data.size() - 1;
            final boolean isZero = data.get(i) == 0;
            if (isZero || isLastElement) {
                final int currentZeroIndex = isLastElement && !isZero ? INF : i;
                for (int k = lastCalculatedIndex + 1; k <= i; k++) {
                    data.set(k, Integer.min(k - zeroIndex, currentZeroIndex - k));
                }
                lastCalculatedIndex = i;
                zeroIndex = i;
            }
        }

        return data;
    }

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int numbersCount = Integer.parseInt(reader.readLine());
            final List<Integer> data = readList(reader);
            printList(getDistanceList(data), writer);
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }

    private static <T> void printList(List<T> list, Writer writer) throws IOException {
        for (T elem : list) {
            writer.write(String.valueOf(elem));
            writer.write(" ");
        }
    }
}
