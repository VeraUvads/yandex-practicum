package practicum.sprint_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConvertToBinary {

    private static String getBinaryNumber(int n) {
        String result = "";

        int divident = n;

        while (divident != 1) {
            result = divident % 2 + result;
            divident = divident / 2;
        }

        result = divident + result;

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            System.out.println(getBinaryNumber(n));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
