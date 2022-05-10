package practicum.sprint_3;

import java.util.Arrays;

public class MergeSorting {

    public static void main(String[] args) {
        int[] a = {1, 4, 9, 2, 10, 11};
        int[] b = merge(a, 0, 3, 6);
        int[] expected = {1, 2, 4, 9, 10, 11};
        for (int i : b) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(Arrays.equals(b, expected));
        int[] c = {4, 5, 3, 0, 1, 2};
        merge_sort(c, 0, 3);
        int[] expected2 = {3, 4, 5, 0, 1, 2};
        for (int i : c) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(Arrays.equals(c, expected2));
    }

    public static void merge_sort(int[] arr, int left, int right) {
        if (right - left <= 1) {
            return;
        }

        int mid = (left + right) / 2;
        merge_sort(arr, left, mid);
        merge_sort(arr, mid, right);
        int[] result = merge(arr, left, mid, right);
        for (int i = 0; i < result.length; i++) {
            arr[i + left] = result[i];
        }
    }

    public static int[] merge(int[] arr, int left, int mid, int right) {
        int[] first = Arrays.copyOfRange(arr, left, mid);
        int[] second = Arrays.copyOfRange(arr, mid, right);
        int[] result = new int[first.length + second.length];

        int index1 = 0;
        int index2 = 0;
        int indexResult = 0;

        while (index1 < first.length && index2 < second.length) {
            int firstValue = first[index1];
            int secondValue = second[index2];
            if (firstValue < secondValue) {
                result[indexResult] = first[index1];
                index1++;
            } else {
                result[indexResult] = second[index2];
                index2++;
            }
            indexResult++;
        }

        while (index1 < first.length) {
            result[indexResult] = first[index1];
            index1++;
            indexResult++;
        }

        while (index2 < second.length) {
            result[indexResult] = second[index2];
            index2++;
            indexResult++;
        }
        return result;
    }

}
