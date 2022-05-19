package practicum.sprint_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PolynomialHash {


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int a = readInt(reader);
            int m = readInt(reader);
            String string = reader.readLine();
            long result = hash(a, m, string);
            System.out.println(result);
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

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }


}
