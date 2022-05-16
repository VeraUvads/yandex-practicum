package practicum.sprint_3.final_task;

import java.io.*;
import java.util.StringTokenizer;
/*
 * https://contest.yandex.ru/contest/23815/run-report/68415612/
 *
 * -- ПРИНЦИП РАБОТЫ --
 * Мы имеем сдвинутый отсортированный список. Так как список отсортирован будет быстро найти элемент используя бинарный поиск.
 * Но так как список сдвинут, нужно будет определить новые корректные границы для поиска.
 *
 * 1) Определяем значения элемента в середине списка.
 * 2) Полученное значение сравнивается с искомым элементом, если оно равно- возвращаем индекс элемента
 * 3) Определяем границы для поиска в  левой  части
 *
 * У нас есть два случая. Первый когда мы ищем в несдвинутом отсортированном списке (left < right). Условие для поиска
 * в левой части определяем как обычно: если наше число находится между левым и средним числом
 *
 * Условие для поиска в левой части во втором случае:
 * Среднее число у нас будет меньше числа с левой границы, и есть два случая: искомое число может быть быть меньше среднего, и больше,
 * в зависимости от того насколько сдвинут список. Поэтому для случая когда больше нам нужно добавить условие что число искомой долджно быть и больше левой границы.
 *
 * 3) Если искомый элемент соответсвует этому условию ищем в левой половине, если нет- в правой.
 * 4) Процесс продолжается до тех пор, пока не будет найден искомы элемент или не станет пустым интервал для поиска.
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Задача состояла в том чтобы за O(log(n)) найти элемент в сдвинутом отсортированном массиве.
 * Массив отсортирован, но сдвинут, поэтому правильно обозначив границы для поиска в левых и правых частях нужно использовать бинарный поиск,
 * поскольку он гарантирует такую временную сложность.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Поскольку тут используется бинарный поиск, с немного измененными границами временная сложность алгоритма составляет O(log(n))
 *
 *-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 *  * Массив, содержащий n элементов, занимает O(n) памяти. Элементы midIndex, mid, left, resultInLeft занимают О(1) каждый.
 * O(n) + O(1)+ O(1)+ O(1)+ O(1) = O(n)
 *
 * */


public class SearchInBrokenList {
    private static final int NOT_FOUND_VALUE = -1;
    private static final int FIRST_POSITION = 0;
    private static final int BORDER_STEP = 1;
    private static final int HALF_DIVIDER = 2;

    public static int brokenSearch(int[] arr, int k) {
        int leftIndex = FIRST_POSITION;
        int rightIndex = arr.length - BORDER_STEP;
        while (leftIndex <= rightIndex) {
            final int midIndex = leftIndex + (rightIndex - leftIndex) / HALF_DIVIDER;
            final int mid = arr[midIndex];

            final int left = arr[leftIndex];
            final boolean defaultCase = k < mid && left <= k;
            final boolean shiftCase = mid < left && (k < mid || left <= k);

            final boolean resultInLeft = defaultCase || shiftCase;

            if (k == mid) {
                return midIndex;
            } else if (resultInLeft) {
                rightIndex = midIndex - BORDER_STEP;
            } else {
                leftIndex = midIndex + BORDER_STEP;
            }
        }

        return NOT_FOUND_VALUE;
    }

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int n = Integer.parseInt(reader.readLine());
            final int k = Integer.parseInt(reader.readLine());
            int[] list = getInputArray(reader, n);

            int result = brokenSearch(list, k);
            System.out.println(result);
        }
    }

    private static int[] getInputArray(final BufferedReader reader, int n) throws IOException {
        final StringTokenizer splitter = new StringTokenizer(reader.readLine(), " ");
        final int[] list = new int[n];

        for (int i = 0; i < n; i++) {
            list[i] = Integer.parseInt(splitter.nextToken());
        }
        return list;
    }
}