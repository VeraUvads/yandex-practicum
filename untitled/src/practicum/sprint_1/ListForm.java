package practicum.sprint_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListForm {

    private static Integer getSum(List<Integer> numberList, int k) {
        StringBuilder d = new StringBuilder();
        for (int i = 0; i < numberList.size(); i++) {
            d.append(numberList.get(i));
        }
        return Integer.parseInt(d.toString()) + k;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int numberLength = readInt(reader);
            List<Integer> numberList = readList(reader);
            int k = readInt(reader);
            String sum = String.valueOf(getSum(numberList, k));
            for (int i = 0; i < sum.length(); i++) {
                writer.write(sum.charAt(i) + " ");
            }
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
