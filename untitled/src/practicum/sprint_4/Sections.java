package practicum.sprint_4;


import java.io.*;
import java.util.*;

public class Sections {

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            Map<String, Integer> map = getInputArray(reader);
            for (String values : map.keySet()) {
                writer.write(values + "\n");
            }
        }
    }

    private static Map<String, Integer> getInputArray(final BufferedReader reader) throws IOException {
        final int n = Integer.parseInt(reader.readLine());
        final Map<String, Integer> map = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String value = reader.readLine();
            if (map.containsKey(value)) {
                map.put(value, map.get(value) + 1);
            } else {
                map.put(value, 0);
            }
        }
        return map;
    }
}
