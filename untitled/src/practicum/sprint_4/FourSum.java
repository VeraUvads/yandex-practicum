package practicum.sprint_4;

import java.io.*;
import java.util.*;

public class FourSum {

    static LinkedHashMap<Fours, Integer> result = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final int n = Integer.parseInt(reader.readLine());
            int s = Integer.parseInt(reader.readLine());
            int[] list = getInputArray(reader, n);
            find(list, s);
            writer.write(result.size() + "\n");
            for (Fours fours : result.keySet()) {
                writer.write(fours.first + " " + fours.second + " " + fours.third + " " + fours.fourth + "\n");
            }
        }
    }

    private static void find(int[] list, int sum) {
        for (int firstIndex = 0; firstIndex < list.length; firstIndex++) {
            long first = list[firstIndex];
            for (int secondIndex = firstIndex + 1; secondIndex < list.length; secondIndex++) {
                long second = list[secondIndex];
                int thirdIndex = secondIndex + 1;
                int fourthIndex = list.length - 1;
                while (thirdIndex < fourthIndex) {
                    long tempSum = first + second + list[thirdIndex] + list[fourthIndex];
                    if (tempSum < sum) {
                        thirdIndex++;
                    } else if (tempSum > sum) {
                        fourthIndex--;
                    } else {
                        Fours key = new Fours((int) first, (int) second, list[thirdIndex], list[fourthIndex]);
                        result.put(key, list[fourthIndex]);
                        thirdIndex++;
                        fourthIndex--;
                    }
                }
            }
        }
    }

//    public static boolean contain(int[] arr, int k, int start) {
//        int leftIndex = start;
//        int rightIndex = arr.length - 1;
//        while (leftIndex <= rightIndex && leftIndex >= start) {
//            final int midIndex = leftIndex + (rightIndex - leftIndex) / 2;
//            final int mid = arr[midIndex];
//
//            if (k == mid) {
//                return true;
//            } else if (k < mid) {
//                rightIndex = midIndex - 1;
//            } else {
//                leftIndex = midIndex + 1;
//            }
//        }
//
//        return false;
//    }


    private static int[] getInputArray(final BufferedReader reader, int n) throws IOException {
        final int[] list = new int[n];
        final StringTokenizer splitter = new StringTokenizer(reader.readLine(), " ");

        for (int i = 0; i < n; i++) {
            list[i] = Integer.parseInt(splitter.nextToken());
        }
        Arrays.sort(list);
        return list;
    }

    public static class Fours implements Comparable<Fours> {
        public int first;
        public int second;
        public int third;
        public int fourth;

        public Fours(int first, int second, int third, int fourth) {
            this.first = first;
            this.second = second;
            this.third = third;
            this.fourth = fourth;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Fours fours = (Fours) o;
            return first == fours.first && second == fours.second && third == fours.third && fourth == fours.fourth;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second, third, fourth);
        }

        @Override
        public int compareTo(Fours fours) {
            if (fours.first == first) {
                return 0;
            }
            if (fours.first >= first) {
                return -1;
            }
            return 1;
        }
    }

}
