package practicum.sprint_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class DegreeFour {

    private static boolean isDegreeOfFour(int n) {
        int remainder = n;
        while (remainder != 1) {
            if (remainder % 4 != 0) {
                return false;
            }
            remainder = remainder / 4;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            if (isDegreeOfFour(n)) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

}
