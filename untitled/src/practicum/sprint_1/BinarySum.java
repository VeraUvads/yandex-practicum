package practicum.sprint_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BinarySum {

    private static String sumOfBinaries(String a, String b) {
        StringBuilder result = new StringBuilder();
        int aIndex = a.length() - 1;
        int bIndex = b.length() - 1;
        char inMemory = '0';

        while (aIndex >= 0 && bIndex >= 0) {
            String sum = sumTwo(a.charAt(aIndex), b.charAt(bIndex));
            if (inMemory != '0' && sum.length() == 2) {
                sum = sum.charAt(0) + sumTwo(inMemory, sum.charAt(1));
                inMemory = '0';
            }

            if (inMemory != '0' && sum.length() == 1) {
                sum = sumTwo(inMemory, sum.charAt(0));
                inMemory = '0';
            }

            if (sum.length() == 2) {
                inMemory = sum.charAt(0);
                sum = sum.substring(1, 2);
            }
            result.insert(0, sum);
            aIndex--;
            bIndex--;
        }

        while (aIndex >= 0) {
            if (inMemory != '0') {
                String sum = sumTwo(a.charAt(aIndex), inMemory);
                if (sum.length() == 2) {
                    inMemory = sum.charAt(0);
                    result.insert(0, sum.substring(1, 2));
                } else {
                    inMemory = '0';
                    result.insert(0, sum);
                }
            } else {
                result.insert(0, a.charAt(aIndex));
            }
            aIndex--;
        }

        while (bIndex >= 0) {
            if (inMemory != '0') {
                String sum = sumTwo(b.charAt(bIndex), inMemory);
                if (sum.length() == 2) {
                    inMemory = sum.charAt(0);
                    result.insert(0, sum.substring(1, 2));
                } else {
                    inMemory = '0';
                    result.insert(0, sum);
                }
            } else {
                result.insert(0, b.charAt(bIndex));
            }
            bIndex--;
        }

        if (inMemory != '0') {
            result.insert(0, inMemory);
        }


        return result.toString();
    }

    private static String sumTwo(char a, char b) {
        if (a == '0') return String.valueOf(b);
        if (b == '0') return String.valueOf(a);
        if (a == '1' && b == '1') return "10";
        return "0";
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String a = reader.readLine();
            String b = reader.readLine();
            System.out.println(sumOfBinaries(a, b));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
