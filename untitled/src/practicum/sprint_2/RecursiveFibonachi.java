package practicum.sprint_2;

import java.io.IOException;
import java.util.Scanner;

public class RecursiveFibonachi {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        System.out.println(getFn(number));
    }

    private static int getFn(int number) {
        if (number == 0 || number == 1) {
            return 1;
        }
        return getFn(number - 1) + getFn(number - 2);
    }

}
