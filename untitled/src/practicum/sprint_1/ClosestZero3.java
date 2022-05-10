package practicum.sprint_1;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClosestZero3 {

    private static List<Integer> getDistanceList(List<Integer> data) {
        int previousZeroIndex = Integer.MIN_VALUE;

        for (int i = 0; i < data.size(); i++) {
            boolean isZero = data.get(i) == 0;
            if (isZero) {
                if (previousZeroIndex == Integer.MIN_VALUE) {
                    previousZeroIndex = -i;
                }

                for (int j = (previousZeroIndex + i) / 2; j < i; j++) {
                    data.set(j, Math.abs(i - j));
                }
                if (previousZeroIndex >= 0) {
                    for (int j = previousZeroIndex + 1; j < (previousZeroIndex + i) / 2; j++) {
                        data.set(j, Math.abs(i - j));
                    }
                }
                previousZeroIndex = i;
            }
        }

        if (previousZeroIndex != data.size() - 1) {
            for (int i = previousZeroIndex + 1; i < data.size(); i++) {
                data.set(i, i - previousZeroIndex);
            }
        }
        return data;
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
