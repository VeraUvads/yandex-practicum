package practicum.sprint_4;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Competition {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] list = getInputArray(reader);
            int result = compute(list);
            System.out.println(result);
        }
    }

    private static int compute(int[] list) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int maxLength = 0;

        for (int i = 0; i < list.length; i++) {
            if (list[i] == 0) {
                sum--;
            } else {
                sum++;
            }
            if (map.containsKey(sum)) {
                int tempMaxLength = i - map.get(sum);
                if (tempMaxLength > maxLength) {
                    maxLength = tempMaxLength;
                }
            } else {
                map.put(sum, i);
            }
            if (sum == 0) {
                if (maxLength < i) {
                    maxLength = i + 1;
                }
            }
        }

        return maxLength;
    }

    private static int[] getInputArray(final BufferedReader reader) throws IOException {
        final int n = Integer.parseInt(reader.readLine());
        final int[] list = new int[n];
        if (n == 0) {
            return list;
        }
        final StringTokenizer splitter = new StringTokenizer(reader.readLine(), " ");

        for (int i = 0; i < n; i++) {
            list[i] = Integer.parseInt(splitter.nextToken());
        }
        return list;
    }
}
