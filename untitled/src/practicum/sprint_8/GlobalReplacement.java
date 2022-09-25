package practicum.sprint_8;

import java.io.*;
import java.util.ArrayList;

public class GlobalReplacement {


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final String text = reader.readLine();
            final String pattern = reader.readLine();
            final String forReplace = reader.readLine();
            final String search = (pattern + "#" + text);
            final ArrayList<Integer> result = findPositions(pattern, search);
            int start = 0;
            for (final Integer position : result) {
                writer.write(text.substring(start, position));
                writer.write(forReplace);
                start = position + pattern.length();
            }
            writer.write(text.substring(start));
        }
    }

    private static ArrayList<Integer> findPositions(String pattern, String search) {
        ArrayList<Integer> result = new ArrayList<>();

        final int[] dp = new int[pattern.length()];
        int prev = 0;
        for (int i = 1; i < search.length(); i++) {
            int k = prev;
            while (k > 0 && search.charAt(k) != search.charAt(i)) {
                k = dp[k - 1]; // все еще не понимаю почему
            }
            if (search.charAt(k) == search.charAt(i)) {
                k++;
            }
            if (i < pattern.length()) {
                dp[i] = k;
            }
            prev = k;
            if (k == pattern.length()) {
//            # Дважды отнимаем от него длину шаблона, чтобы получить позицию начала:
//            #  - чтобы «переместиться» на начало найденного шаблона,
//            #  - чтобы не учитывать добавленное "pattern#".
                result.add(i - 2 * pattern.length());
            }
        }
        return result;
    }
}
