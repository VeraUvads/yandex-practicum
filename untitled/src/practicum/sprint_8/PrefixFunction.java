package practicum.sprint_8;

import java.io.*;

public class PrefixFunction {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final char[] line = reader.readLine().toCharArray();
            final int[] dp = new int[line.length];
            writer.write(0 + " ");
            for (int i = 1; i < line.length; i++) {
                int k = dp[i - 1];
                while (k > 0 && line[k] != line[i]) {// почему
                    k = dp[k - 1];// почему
                }
                if (line[k] == line[i]) {
                    k++;
                }
                dp[i] = k;
                writer.write(k + " ");
            }
        }
    }
}
