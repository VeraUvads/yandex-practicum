package practicum.sprint_3;

import java.util.Scanner;

public class Combination {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] list = scanner.nextLine().toCharArray();
        findCombination(list, 0, "");

    }

    private static void findCombination(char[] list, int index, String line) {
        if (index != list.length) {
            char symbol = list[index];
            findCombination(list, index + 1, line + getPossibleSymbols(symbol, 0));
            findCombination(list, index + 1, line + getPossibleSymbols(symbol, 1));
            findCombination(list, index + 1, line + getPossibleSymbols(symbol, 2));
            if (symbol == '7' || symbol == '9') {
                findCombination(list, index + 1, line + getPossibleSymbols(symbol, 3));
            }
        } else {
            System.out.print(line + " ");
        }
    }

    private static char getPossibleSymbols(char symbol, int index) {
        switch (symbol) {
            case '2':
                return "abc".charAt(index);
            case '3':
                return "def".charAt(index);
            case '4':
                return "ghi".charAt(index);
            case '5':
                return "jkl".charAt(index);
            case '6':
                return "mno".charAt(index);
            case '7':
                return "pqrs".charAt(index);
            case '8':
                return "tuv".charAt(index);
            case '9':
                return "wxyz".charAt(index);
        }
        return '-';
    }

}
