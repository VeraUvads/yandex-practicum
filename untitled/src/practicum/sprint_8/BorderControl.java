package practicum.sprint_8;

import java.io.*;

public class BorderControl {

    private static int counter = 0;
    private static final String FAIL = "FAIL";
    private static final String OK = "OK";


    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] strings = getInputData(bufferedReader);
            char[] first = strings[0].toCharArray();
            char[] second = strings[1].toCharArray();
            int firstLength = first.length;
            int secondLength = second.length;
            if (firstLength - secondLength > 1) {
                System.out.println(FAIL);
                return;
            }
            int length = Math.min(firstLength, secondLength);
            int firstIndex = 0;
            int secondIndex = 0;
            for (int i = 0; i < length; i++) {
                if (first[firstIndex] != second[secondIndex]) {
                    counter++;
                    if (firstLength > secondLength) {
                        firstIndex++;
                    } else if (secondLength > firstLength) {
                        secondIndex++;
                    }
                }
                firstIndex++;
                secondIndex++;
                if (counter > 1) {
                    System.out.println(FAIL);
                    return;
                }
            }
            System.out.println(OK);
        }
    }

    private static String[] getInputData(final BufferedReader reader) throws IOException {
        String[] array = new String[2];
        array[0] = reader.readLine();
        array[1] = reader.readLine();
        return array;
    }
}
