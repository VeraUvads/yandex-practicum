package practicum.sprint_7.final_task;

/* https://contest.yandex.ru/contest/25597/run-report/70427573/
*
* -- ПРИНЦИП РАБОТЫ --
*
* Используем одномерный dp, длина которого - полусумма исходных чисел.
* Нужно проходить по нему справа налево и заполнять ячейки true или false соответственно тому,
* можем ли получить величину ячейки суммой текущего числа и уже сохраненными. По последней ячейке смотрим ответ,
* если там больше одного раза накапливается значение. В цикле на каждой остановке в dp делается проверка: если от текущего индекса ячейки
* отнять текущее число из последовательности, то получим индекс, в котором  либо true либо false. если там true - значит суммой
* с этим числом мы получаем число в текущей ячейке dp. Но если сумма всех чисел не является кратной двум,
* можно сразу вернуть false, потому что нельзя нацело и поровну разделить очки.
*
* Базовый случай dp[0] = true;
*
* -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
* https://ru.wikipedia.org/wiki/Задача_разбиения_множества_чисел
*
* -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
* Мы используем для решения задачи два вложенных цикла, где внешний это элементы массива со значениями очков с количеством n, и внутренний
* размер суммы s.
* Временная сложность равна O(n*s)
*
* -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
* Мы храним одномерный массив dp, длина которого - полусумма исходных чисел.
* O(s/2), для s - размер суммы.
*
*/

import java.io.*;
import java.util.*;

public class SameSum {

    private final static String TRUE = "True";
    private final static String FALSE = "False";
    private static int sum = 0;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final int[] values = getInputArray(reader);
            if (values.length == 0) {
                return;
            }
            final int halfSum = sum / 2;
            final boolean[] dp = new boolean[halfSum + 1];
            dp[0] = true;

            final boolean isSameSum = isSameSum(values, dp, halfSum);

            if (isSameSum) {
                writer.write(TRUE);
            } else {
                writer.write(FALSE);
            }
        }
    }

    private static boolean isSameSum(int[] values, boolean[] dp, int halfSum) {
        if (sum % 2 != 0) {
            return false;
        }
        for (final int value : values) {
            for (int i = halfSum; i > value - 1; i--) {
                dp[i] = dp[i - value] || dp[i];
                if (i == halfSum && dp[i]) {
                    return true;
                }
            }
        }
        return dp[dp.length - 1];
    }

    private static int[] getInputArray(final BufferedReader reader) throws IOException {
        final int count = Integer.parseInt(reader.readLine());
        final int[] array = new int[count];
        final StringTokenizer splitter = new StringTokenizer(reader.readLine(), " ");

        for (int i = 0; i < count; i++) {
            final int value = Integer.parseInt(splitter.nextToken());
            array[i] = value;
            sum += value;
        }
        return array;
    }

}
