package practicum.sprint_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BracketGenerator {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            generateBrackets(n * 2, "", 0);
        }
    }

    private static void generateBrackets(int n, String s, int count) {
        if (n == 0 && count == 0) {
            System.out.println(s);
        } else if (n >= count && count >= 0) {
            generateBrackets(n - 1, s + "(", count + 1);
            generateBrackets(n - 1, s + ")", count - 1);
        }
    }
}
