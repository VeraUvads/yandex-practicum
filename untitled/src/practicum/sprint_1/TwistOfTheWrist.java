package practicum.sprint_1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TwistOfTheWrist {

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final int k = Integer.parseInt(reader.readLine());
            final List<Integer> sortedData = readList(reader);
            final int result = countPoints(k, sortedData);
            System.out.println(result);
        }
    }

    private static int countPoints(int k, List<Integer> sortedData) {
        int result = 0;
        final int maxTouches = k * 2;
        final int[] digitsCounter = new int[10];

        for (int i = 0, max = sortedData.size(); i < max; ++i) {
            digitsCounter[sortedData.get(i)]++;
        }

        for (int i = 0, max = digitsCounter.length; i < max; ++i) {
            if (digitsCounter[i] > 0 && digitsCounter[i] <= maxTouches) {
                ++result;
            }
        }

        return result;
    }


    private static List<Integer> readList(BufferedReader reader) throws IOException {
        final List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.addAll(readLine(reader));
        }
        return list;
    }

    private static List<Integer> readLine(BufferedReader reader) throws IOException {
        final String line = reader.readLine();
        final List<Integer> list = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            final char symbol = line.charAt(i);
            if (symbol != '.') {
                list.add(Integer.parseInt(String.valueOf(symbol)));
            }
        }
        return list;
    }
}
