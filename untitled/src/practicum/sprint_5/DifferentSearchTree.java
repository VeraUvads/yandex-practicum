package practicum.sprint_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DifferentSearchTree {
    // not finished

    static int possibleCountOfTrees = 0;
    static Set<String> set = new HashSet<>();
    static int n;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine().trim());
            ArrayList<Integer> numbers = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                numbers.add(i);
            }
            findWay(numbers, "", null, null);
            System.out.println(set.size());
        }
    }

    private static void findWay(ArrayList<Integer> numbers, String result, Integer parent, Integer grandparent) { // previous?
        if (numbers.isEmpty()) {
            possibleCountOfTrees++;
            set.add(result);
            System.out.println(result);
            return;
        }
        for (int i = 0; i < numbers.size(); i++) {
            int temp = numbers.remove(i);
            if (parent == null) {
                findWay(numbers, temp + result, temp, parent);
                findWay(numbers, temp + result, temp, parent);
            } else if (grandparent == null) {
                if (temp < parent) {
                    findWay(numbers, temp + result, temp, parent);
                }
                if (temp > parent) {
                    findWay(numbers, temp + result, temp, parent);
                }
            } else {
                if (temp < parent && temp < grandparent) {
                    findWay(numbers, temp + result, temp, parent);
                }
                if (temp > parent && temp > grandparent) {
                    findWay(numbers, temp + result, temp, parent);
                }
            }
            numbers.add(i, temp);
        }

    }
}
