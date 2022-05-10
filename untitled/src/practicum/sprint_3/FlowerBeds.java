package practicum.sprint_3;

import java.io.*;
import java.util.*;

public class FlowerBeds {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final List<List<Integer>> matrix = readMatrix(reader);
            final List<List<Integer>> result = merge(sort(matrix, 0, matrix.size() - 1));

            for (List<Integer> list : result) {
                printList(list, writer);
                writer.write("\n");
            }
        }
    }

    private static List<List<Integer>> merge(List<List<Integer>> matrix) {
        final List<List<Integer>> result = new ArrayList<>();

        for (List<Integer> newList : matrix) {
            if (result.isEmpty()) {
                result.add(newList);
            } else {
                List<Integer> previousItem = result.get(result.size() - 1);
                int previous0 = previousItem.get(0);
                int new0 = newList.get(0);
                int previous1 = previousItem.get(1);
                int new1 = newList.get(1);
                if (new0 > previous0 && new1 > previous1 && previous1 < new0) {
                    result.add(newList);
                } else {
                    newList.set(0, Math.min(previous0, new0));
                    newList.set(1, Math.max(previous1, new1));
                    result.set(result.size() - 1, newList);
                }
            }
        }

        return result;
    }


    public static List<List<Integer>> sort(List<List<Integer>> arr, int left, int right) {
        if (right <= left) {
            List<List<Integer>> list = new ArrayList<>(1);
            list.add(arr.get(left));
            return list; // или интервал
        }

        int mid = (left + right) / 2;
        List<List<Integer>> first = sort(arr, left, mid);
        List<List<Integer>> second = sort(arr, mid + 1, right);
        List<List<Integer>> result = new ArrayList<>(first.size() + second.size());

        int index1 = 0;
        int index2 = 0;
        int indexResult = 0;

        while (index1 < first.size() && index2 < second.size()) {
            List<Integer> firstValue = first.get(index1);
            List<Integer> secondValue = second.get(index2);
            if (firstValue.get(0) < secondValue.get(0)) {
                result.add(indexResult, first.get(index1));
                index1++;
            } else if (secondValue.get(0) <= firstValue.get(0)) {
                result.add(indexResult, second.get(index2));
                index2++;
            }
            indexResult++;
        }

        while (index1 < first.size()) {
            result.add(indexResult, first.get(index1));
            index1++;
            indexResult++;
        }

        while (index2 < second.size()) {
            result.add(indexResult, second.get(index2));
            index2++;
            indexResult++;
        }

        return result;
    }


    private static boolean compare(List<Integer> first, List<Integer> second) {
        return first.get(0) > second.get(0);
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<List<Integer>> readMatrix(BufferedReader reader) throws IOException {
        List<List<Integer>> matrix = new ArrayList<>();
        final int rowsCount = Integer.parseInt(reader.readLine());
        for (int i = 0; i < rowsCount; i++) {
            final StringTokenizer splitter = new StringTokenizer(reader.readLine(), " ");
            matrix.add(readList(splitter));
        }
        return matrix;
    }


    private static ArrayList<Integer> readList(StringTokenizer splitter) throws IOException {
        final ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            list.add(Integer.parseInt(splitter.nextToken()));
        }
        return list;
    }

    private static <T> void printList(List<T> list, BufferedWriter writer) {
        list.forEach(elem -> {
            try {
                writer.write(elem + " ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
