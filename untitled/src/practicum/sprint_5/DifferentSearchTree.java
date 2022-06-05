package practicum.sprint_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DifferentSearchTree {

    static int possibleCountOfTrees = 0;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine().trim());
            findWay(1, n);
            System.out.println(possibleCountOfTrees);
        }
    }

    private static void findWay(int from, int to) { // previous?
        if (to - from == 1) {
            possibleCountOfTrees++;
            possibleCountOfTrees++;
            return;
        }
        findWay(from, to);


    }
}
