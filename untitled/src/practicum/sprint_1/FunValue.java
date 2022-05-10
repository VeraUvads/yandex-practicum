package practicum.sprint_1;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FunValue {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            List<Integer> data = readList(reader);
            int a = data.get(0);
            int x = data.get(1);
            int b = data.get(2);
            int c = data.get(3);
            int y = countFunValue(a, x, b, c);
            writer.write(String.valueOf(y));
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }

    private static int countFunValue(int a, int x, int b, int c) {
        return a * x * x + b * x + c;
    }
}
