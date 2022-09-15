package practicum.sprint_8;

import java.io.*;

public class LineReversal {

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] array = getInputArray(bufferedReader);
            for (int i = array.length - 1; i >= 0; i--) {
                writer.write(array[i] + " ");
            }
        }
    }


    private static String[] getInputArray(final BufferedReader reader) throws IOException {
        return reader.readLine().split(" ");
    }
}
