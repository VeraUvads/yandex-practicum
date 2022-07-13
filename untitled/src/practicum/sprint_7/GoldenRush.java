package practicum.sprint_7;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GoldenRush {

    private static List<Gold> list;
    private static long result = 0;
    private static int capacity = 0;

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            capacity = Integer.parseInt(reader.readLine());
            list = getInputArray(reader);
            countResult();
            System.out.println(result);
        }
    }

    private static void countResult() {
        Collections.sort(list);
        for (Gold gold : list) {
            if (capacity <= 0) break;
            int takeFromHeap = Math.min(gold.amount, capacity);
            capacity -= takeFromHeap;
            result += takeFromHeap * gold.cost;
        }
    }

    private static ArrayList<Gold> getInputArray(final BufferedReader reader) throws IOException {
        final int n = Integer.parseInt(reader.readLine());
        final ArrayList<Gold> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] line = reader.readLine().split(" ");
            list.add(new Gold(line));
        }
        return list;
    }

    static class Gold implements Comparable<Gold> {
        int amount;
        long cost;

        Gold(String[] line) {
            this.amount = Integer.parseInt(line[1]);
            this.cost = Long.parseLong(line[0]);
        }

        @Override
        public int compareTo(Gold gold) {
            return (int) (gold.cost - cost);
        }
    }
}
