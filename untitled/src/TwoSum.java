import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TwoSum {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int length = Integer.parseInt(reader.readLine());
            List<Integer> data = readList(reader);
            int result = Integer.parseInt(reader.readLine());
            printList(countTwoSum(result, data), writer);
        }
    }

    private static List<Integer> countTwoSum(int result, List<Integer> data) {
        List<Integer> resultList = new ArrayList<>();


        for (int i = 0; i < data.size(); i++) {
            for (int j = i + 1; j < data.size(); j++) {
                if (data.get(i) + data.get(j) == result) {
                    resultList.add(data.get(i));
                    resultList.add(data.get(j));
                    return resultList;
                }
            }
        }

        return resultList;
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
        if (list.size() == 0){
            try {
                writer.write("None");
            } catch (IOException e) {

            }
        }
    }
}
