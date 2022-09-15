package practicum.sprint_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CharsComparing { // что то не так


    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] strings = getInputData(bufferedReader);
            char[] first = strings[0].toCharArray();
            char[] second = strings[1].toCharArray();
            int firstLength = first.length;
            int secondLength = second.length;
            int firstResult = 0;
            int secondResult = 0;
            int length = Math.max(firstLength, secondLength);
            for (int index = 0; index < length; index++) {
                if (index < firstLength) {
                    char firstLetter = first[index];
                    if (firstLetter % 2 == 0) {
                        firstResult += firstLetter;
                    }
                }
                if (index < secondLength) {
                    char secondLetter = second[index];
                    if (secondLetter % 2 == 0) {
                        secondResult += secondLetter;
                    }
                }
            }

            if (firstResult == secondResult) {
                System.out.println("0");
            } else if (firstResult > secondResult) {
                System.out.println("1");
            } else {
                System.out.println("-1");
            }
        }
    }


    private static String[] getInputData(final BufferedReader reader) throws IOException {
        String[] array = new String[2];
        array[0] = reader.readLine();
        array[1] = reader.readLine();
        return array;
    }
}
