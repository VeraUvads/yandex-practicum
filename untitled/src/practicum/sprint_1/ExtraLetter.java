package practicum.sprint_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ExtraLetter {
    private static char getExcessiveLetter(String s, String t) {
        String sortedS = sortString(s);
        String sortedT = sortString(t);
        int index = 0;
        while (index < sortedS.length() && index < sortedT.length()) {
            if (sortedS.charAt(index) != sortedT.charAt(index)) {
                if (sortedS.charAt(index) < sortedT.charAt(index)) {
                    return sortedS.charAt(index);
                } else {
                    return sortedT.charAt(index);
                }
            } else {
                index++;
            }
        }

        if (index < sortedS.length()) {
            return sortedS.charAt(index);
        }

        if (index < sortedT.length()) {
            return sortedT.charAt(index);
        }

        return '0';
    }

    private static String sortString(String s) {
        char []arr = s.toCharArray();
        Arrays.sort(arr);
        return String.valueOf(arr);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s = reader.readLine();
            String t = reader.readLine();
            System.out.println(getExcessiveLetter(s, t));

        }
    }
}
