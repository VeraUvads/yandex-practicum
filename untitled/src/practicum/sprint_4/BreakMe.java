package practicum.sprint_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BreakMe {

    public static void main(String[] args) throws IOException {
        generateString(1000, 123987123);
    }

    private static void generateString(int a, int mod) {
        RandomString random = new RandomString(10);
        String randomString = random.nextString();
        Map<Long, String> map = new HashMap<>();
        long result1 = hash(a, mod, randomString);
        map.put(result1, randomString);
        while (true) {
            String string2 = random.nextString();
            long result = hash(a, mod, string2);

            if (map.containsKey(result) && !Objects.equals(map.get(result), string2)) {
                System.out.println(string2);
                System.out.println(map.get(result));
                break;
            } else {
                map.put(result, string2);
            }
        }

    }

    private static void compare(BufferedReader reader) throws IOException {
        String[] strings = reader.readLine().split(" ");
        Map<Long, String> map = new HashMap<>();
        for (String string : strings) {
            long result = hash(1000, 123987123, string);
            System.out.println(result);

            if (map.containsKey(result) && !Objects.equals(map.get(result), string)) {
                System.out.println(string);
                System.out.println(map.get(result));
                break;
            } else {
                map.put(result, string);
            }
        }
    }


    private static long hash(int a, int m, String string) {
        long hash = 0;
        int stringLength = string.length();
        long pow = 1;
        for (int i = stringLength - 1; i >= 0; i--) {
            long s = string.charAt(i);
            hash += (pow * s) % m;
            pow = pow * a % m;
        }

        return hash % m;
    }
}
