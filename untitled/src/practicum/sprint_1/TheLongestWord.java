package practicum.sprint_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TheLongestWord {

    private static String getLongestWord(List<String> data) {
        String longestWork = "";
        for (String datum : data) {
            if (datum.length() > longestWork.length()) {
                longestWork = datum;
            }
        }
        return longestWork;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int textLength = readInt(reader);
            List<String> data = readAsList(reader);
//            String longestWord = data.get(data.size() - 1);
            String longestWord = getLongestWord(data);
            System.out.println(longestWord);
            System.out.println(longestWord.length());
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<String> readAsList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> String.valueOf(elem))
//                .sorted(new Comparator<String>() {
//                    @Override
//                    public int compare(String o1, String o2) {
//                        return o1.length() < o2.length() ? -1 : o1.length() == o2.length() ? 0 : 1;
//                    }
//                })
                .collect(Collectors.toList());
    }
}
