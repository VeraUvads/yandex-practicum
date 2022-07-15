package practicum.sprint_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JumpOverStair {

    private static long[] dp;
    private static long mod = 1_000_000_007;
    private static int possibleWay;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] line = reader.readLine().split(" ");
            int stairCount = Integer.parseInt(line[0]);
            possibleWay = Integer.parseInt(line[1]);
            dp = new long[stairCount];
            dp[0] = 1L;
            long answer = sum(stairCount - 1);
            System.out.println(answer);
        }
    }

    private static long sum(int index) {
        long sum = 0;
        for (int i = 1; i <= possibleWay; i++) {
            if (index - i >= 0) {
                if (dp[index - i] == 0) {
                    dp[index - i] = sum(index - i);
                }
                long forPlus = dp[index - i];
                sum += forPlus;
            }
        }
        return sum % mod;
    }
}
