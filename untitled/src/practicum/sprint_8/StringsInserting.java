package practicum.sprint_8;

import java.io.*;
import java.util.StringTokenizer;

public class StringsInserting { // чуть-чуть не проходит по времени

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String resultString = buildString(bufferedReader);
            writer.write(resultString);
        }
    }


    private static String buildString(final BufferedReader reader) throws IOException {
        String defaultLine = reader.readLine();
        final StringBuilder builder = new StringBuilder(defaultLine);
        final int n = Integer.parseInt(reader.readLine());
        final int[] indexBuffer = new int[defaultLine.length() + 1];
        for (int i = 1; i < indexBuffer.length; i++) {
            indexBuffer[i] = i;
        }

        for (int i = 0; i < n; i++) {
            final StringTokenizer splitter = new StringTokenizer(reader.readLine(), " ");
            final String line = splitter.nextToken();
            final int index = Integer.parseInt(splitter.nextToken());
            builder.insert(indexBuffer[index], line);
            for (int j = index; j <= defaultLine.length(); j++) {
                indexBuffer[j] += line.length();
            }
        }
        return builder.toString();
    }
}
