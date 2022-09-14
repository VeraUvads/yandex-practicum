package practicum.sprint_7.final_task;


import java.io.*;
import java.util.*;

public class SameSum {


    private final static String TRUE = "True";
    private final static String FALSE = "False";
    private static int sum = 0;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final int[] values = getInputArray(reader);
            if (values.length == 0) return;
            final int halfSum = sum / 2;
            final boolean[] dp = new boolean[halfSum + 1];
            dp[0] = true;

            final boolean isSameSum = isSameSum(values, dp, halfSum);

            if (isSameSum) {
                writer.write(TRUE);
            } else {
                writer.write(FALSE);
            }
        }
    }

    private static boolean isSameSum(int[] values, boolean[] dp, int halfSum) {
        if (sum % 2 != 0) return false;
        for (int value : values) {
            for (int i = halfSum; i > value - 1; i--) {
                dp[i] = dp[i - value] || dp[i];
                if (i == halfSum && dp[i])
                    return true;
            }
        }

        return dp[dp.length - 1];
    }

    private static int[] getInputArray(final BufferedReader reader) throws IOException {
        int count = Integer.parseInt(reader.readLine());
        int[] array = new int[count];
        final StringTokenizer splitter = new StringTokenizer(reader.readLine(), " ");

        for (int i = 0; i < count; i++) {
            int value = Integer.parseInt(splitter.nextToken());
            array[i] = value;
            sum += value;
        }
        return array;
    }

}
