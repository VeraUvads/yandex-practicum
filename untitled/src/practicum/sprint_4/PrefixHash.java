package practicum.sprint_4;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PrefixHash {
    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int a = Integer.parseInt(reader.readLine());
            int mod = Integer.parseInt(reader.readLine());
            String string = reader.readLine();

            Map<String, Long> map = new HashMap<>();
            fillMap(a, mod, string, map);
//
//            for (String key : map.keySet()) {
//                System.out.println(key + " " + map.get(key));
//            }

            int n = Integer.parseInt(reader.readLine());
            for (int i = 0; i < n; i++) {
                String[] range = reader.readLine().split(" ");
                int first = Integer.parseInt(range[0]) - 1;
                int second = Integer.parseInt(range[1]);
                String substring = string.substring(first, second);
                System.out.println(map.get(substring));
            }
        }
    }

    private static void fillMap(int a, int mod, String string, Map<String, Long> map) {
        int length = string.length();
        for (int i = length - 1; i >= 0; i--) {
            char s = string.charAt(i);
            long pow = 1;
            for (int degree = 1; degree < length - i; degree++) { // todo не уверена
                pow = pow * a % mod;
            }
//            System.out.println("pow " + pow);
//            System.out.println("degree " + (length - i-1));
            long hash = (pow * s) % mod;
            map.put(String.valueOf(s), hash);
            for (int j = i - 1; j >= 0; j--) {
                pow = (pow * a) % mod;
//                System.out.println("degree " + (length - j-1));
//                System.out.println("pow " + pow);
                char s2 = string.charAt(j);
                hash += (pow * s2) % mod;
                String key = string.substring(j, i + 1);
                map.put(key, hash);
            }
        }
    }


//    private static long hash(int a, int m, char s, long previousHash, int i) {
//        long hash = previousHash;
//        long pow = 1;
//        for (int i = stringLength - 1; i >= 0; i--) {
//            hash += (pow * s) % m;
//            pow = pow * a % m;
//        }
//        return hash % m;
//    }

}
