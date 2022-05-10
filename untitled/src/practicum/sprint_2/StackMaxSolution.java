package practicum.sprint_2;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

public class StackMaxSolution {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int commandsCount = scanner.nextInt();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.contains("pop")) {
                StackMax.pop();
            }
            if (line.contains("get_max")) {
                StackMax.getMax();
            }
            if (line.contains("push")) {
                int x = Integer.parseInt(line.replace("push ", ""));
                StackMax.push(x);
            }
        }
    }

    public static class StackMax {
        private static final Stack<Integer> _stack = new Stack<>();
        private static final Stack<Integer> maxStack = new Stack<>();

        public static void push(int x) {
            if (maxStack.isEmpty() || x >= maxStack.peek()) {
                maxStack.push(x);
            }
            _stack.push(x);
        }

        public static void pop() {
            if (_stack.isEmpty()) {
                System.out.println("error");
            } else {
                if (Objects.equals(_stack.peek(), maxStack.peek())) {
                    maxStack.pop();
                }
                _stack.pop();
            }
        }

        public static void getMax() {
            if (_stack.isEmpty()) {
                System.out.println("None");
            } else {
                System.out.println(maxStack.peek());
            }
        }
    }
}

