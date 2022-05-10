
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class MovingAverage {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int secondCount = Integer.parseInt(reader.readLine());
            List<Integer> data = readList(reader);
            int k = Integer.parseInt(reader.readLine());
            printList(countMovingAverage(secondCount, data, k), writer);
        }
    }

    private static List<Float> countMovingAverage(int secondCount, List<Integer> data, int k) {
        List<Float> result = new ArrayList<>();

        float currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += data.get(i);
        }

        result.add(currentSum / k);
        for (int i = 1; i + k - 1 < data.size(); i++) {
            currentSum += data.get(i + k - 1) - data.get(i - 1);
            result.add(currentSum / k);
        }
        return result;
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
