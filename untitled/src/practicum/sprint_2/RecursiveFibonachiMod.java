package practicum.sprint_2;

import java.io.IOException;
import java.util.Scanner;

public class RecursiveFibonachiMod {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        long number = scanner.nextLong();
        int k = scanner.nextInt();
        System.out.println(getFn(number, (int) Math.pow(10, k)));
    }

    private static long getFn(long number, int k) {
        long resultN2 = 0;
        long resultN1 = 1;
        for (int i = 0; i < number; i++) {
            long temp = resultN1;
            resultN1 += resultN2 % k;
            resultN2 = temp;

        }
        return resultN1 % k;
    }
}
