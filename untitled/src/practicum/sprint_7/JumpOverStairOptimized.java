package practicum.sprint_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JumpOverStairOptimized {

    private static long[] dp;
    private static int possibleWay;
    private static long mod = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] line = reader.readLine().split(" ");
            int stairCount = Integer.parseInt(line[0]);
            possibleWay = Integer.parseInt(line[1]);
            dp = new long[stairCount];
            dp[0] = 1L;
            dp[1] = 1L;
            long answer = getByIndex(stairCount - 1);
            System.out.println(answer);
        }
    }

    private static long getByIndex(int index) {
        if (index < 0 || index >= dp.length) return 0;
        if (dp[index] == 0) {
            dp[index] = (2 * getByIndex(index - 1)) % mod + mod - getByIndex(index - possibleWay - 1);
        }

        return dp[index] % mod;
    }
}
