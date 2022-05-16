package practicum.sprint_4;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WeirdComparing {

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String first = reader.readLine();
            String second = reader.readLine();
            boolean isEqual = compare(first, second);
            if (isEqual) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean compare(String first, String second) {
        Map<String, String> equivalentMap = new HashMap<>();
        int length = first.length();
        if (length != second.length()) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            String key = String.valueOf(first.charAt(i));
            String value = String.valueOf(second.charAt(i));
            if ((equivalentMap.containsKey(key) || equivalentMap.containsValue(value)) && !Objects.equals(equivalentMap.get(key), value)) {
                return false;
            } else {
                equivalentMap.put(key, value);
            }
        }
        return true;
    }
}
