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
            int result = hash(a, m, string);
            System.out.println(result);
        }
    }

    private static int hash(int a, int m, String string) {
        int hash = 0;
        int stringLength = string.length();
        for (int i = 0; i < stringLength; i++) {
            hash += string.charAt(i) * (Math.pow(a, stringLength - i - 1));
        }

        return hash % m;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }


}
