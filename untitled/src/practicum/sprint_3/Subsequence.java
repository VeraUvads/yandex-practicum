package practicum.sprint_3;

import java.io.*;

public class Subsequence {


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line1 = reader.readLine();
            String line2 = reader.readLine();
            boolean isSubsequence = check(line1, line2);
            if (isSubsequence) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }

    private static boolean check(String line1, String line2) {
        if (line1.length() > line2.length()) {
            return false;
        }
        int index1 = 0;
        int index2 = 0;

        while (index1 < line1.length()) {
            if (index2 == line2.length()) {
                return false;
            }
            if (line1.charAt(index1) == line2.charAt(index2)) {
                ++index1;
            }
            ++index2;
        }
        return true;
    }
}
