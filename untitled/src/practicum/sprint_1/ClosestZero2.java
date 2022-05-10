package practicum.sprint_1;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClosestZero2 {

    private static List<Integer> getDistanceList(List<Integer> data) {
        int previousZeroIndex = -999999;
        int lastCalculatedIndex = -1;
        for (int i = 0; i < data.size(); i++) {
            boolean isZero = data.get(i) == 0;
            boolean isLastElement = i == data.size() - 1;
            if (isZero || isLastElement) {
                int currentZeroIndex = isLastElement && !isZero ? 99999999 : i;
                for (int k = lastCalculatedIndex+1; k <= i; k++) {
                    data.set(k, Integer.min(k - previousZeroIndex, currentZeroIndex - k));
                }
                lastCalculatedIndex = i;
                previousZeroIndex = i;
            }
        }
        return data;
    }

    private static int divide(int divident, int divisor) {
        return (int) Math.ceil((double) divident / divisor);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int numberLength = Integer.parseInt(reader.readLine());
            List<Integer> data = readList(reader);
            printList(getDistanceList(data), writer);
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }

    private static <T> void printList(List<T> list, Writer writer) {
        list.forEach(elem -> {
                    try {
                        writer.write(String.valueOf(elem));
                        writer.write(" ");
                    } catch (IOException e) {
                    }
                }
        );
    }
}
