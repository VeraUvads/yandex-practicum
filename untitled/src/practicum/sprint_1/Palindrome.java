package practicum.sprint_1;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Palindrome {

    private static boolean isPalindrome(String notFormatText) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < (notFormatText.length()); i++) {
            if (isLetterOrNumber(notFormatText.charAt(i))) {
                text.append(notFormatText.charAt(i));
            }
        }
        for (int i = 0; i < (text.length() / 2); i++) {
            if (text.charAt(i) != text.charAt(text.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isLetterOrNumber(char symbol) {
        return symbol <= 'z' && symbol >= 'a' || symbol <= '9' && symbol >= '0';
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String text = reader.readLine().toLowerCase();
            if (isPalindrome(text)) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }
}
