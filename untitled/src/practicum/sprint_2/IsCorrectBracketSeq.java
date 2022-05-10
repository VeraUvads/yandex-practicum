package practicum.sprint_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Stack;

public class IsCorrectBracketSeq {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            if (is_correct_bracket_seq(line)) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }

    private static Boolean is_correct_bracket_seq(String line) {
        int counter1 = 0;
        int counter2 = 0;
        int counter3 = 0;
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < line.length(); i++) {
            char symbol = line.charAt(i);
            if (symbol == '(') {
                stack.push("(");
                counter1++;
            }
            if (symbol == ')') {
                if (counter1 == 0 || stack.isEmpty() || !Objects.equals(stack.pop(), "(")) return false;
                counter1--;
            }
            if (symbol == '{') {
                stack.push("{");
                counter2++;
            }
            if (symbol == '}') {
                if (counter2 == 0 || stack.isEmpty() || !Objects.equals(stack.pop(), "{")) return false;
                counter2--;
            }
            if (symbol == '[') {
                stack.push("[");
                counter3++;
            }
            if (symbol == ']') {
                if (counter3 == 0 || stack.isEmpty() || !Objects.equals(stack.pop(), "[")) return false;
                counter3--;
            }
        }
        return counter1 == 0 && counter2 == 0 & counter3 == 0;
    }

}
