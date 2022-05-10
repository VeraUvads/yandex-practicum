package practicum.sprint_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TwoBicycles {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] list = readList(reader);
            int price = readInt(reader);
            int result1 = find(list, price, 0, list.length - 1, list.length - 1);
            int result2 = find(list, price * 2, result1 + 1, list.length - 1, list.length - 1);
            if (result1 != -1) {
                result1++;
            }
            if (result2 != -1) {
                result2++;
            }
            System.out.println(result1 + " " + result2);
        }
    }

    private static int find(int[] list, int price, int leftIndex, int rightIndex, int closestIndex) {
        if (leftIndex >= rightIndex) {
            return closestIndex;
        }
        int right = list[rightIndex];
        if (leftIndex < 0 || price > right) {
            return -1;
        }
        int midIndex = (leftIndex + rightIndex) / 2;
        int mid = list[midIndex];
        int closest = list[closestIndex];

        if (mid <= closest && mid >= price && closestIndex > midIndex) {
            closestIndex = midIndex;
        }

        if (mid < price) {
            return find(list, price, midIndex + 1, rightIndex, closestIndex);
        } else {
            return find(list, price, leftIndex, midIndex, closestIndex);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readList(final BufferedReader reader) throws IOException {
        final int n = Integer.parseInt(reader.readLine());
        final StringTokenizer splitter = new StringTokenizer(reader.readLine(), " ");
        final int[] prices = new int[n];

        for (int i = 0; i < n; i++) {
            prices[i] = Integer.parseInt(splitter.nextToken());
        }
        return prices;
    }
}
