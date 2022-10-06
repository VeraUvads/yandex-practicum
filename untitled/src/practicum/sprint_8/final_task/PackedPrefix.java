package practicum.sprint_8.final_task;

/* https://contest.yandex.ru/contest/26133/run-report/71355684/
*
* -- ПРИНЦИП РАБОТЫ --
* Алгоритм определения самого длинного общего префикса с использованием сортировки
* 1) Определим базовые случаи (n = 0,1). Если размер массива строк равен 0, возвращается пустая строка. Если размер массива строк равен 1,
*  вернуть в нем единственную строку в качестве ответа (префиксную строку).
* 2) Отсортируем массив строк.
* 3) Возьмем первую и последнюю строки из массива.
* 4) Найдем самый длинный общий префикс для этих строк.
*
* -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
* https://ru.tutorialcup.com/interview/string/longest-common-prefix-using-sorting.htm
*
* -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
* При распаковке: O(m)
* При поиске максимального префикса:
* Сортировка массива O(log(m)) для каждой строки, сделанная n раз ==  O(log(m)) * O(n), и проход по строке O(m),
* где n - количество строк, а m - длинна самой длинной строки
*
*
* -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
* Храним массив строк O(n), где n- количество строк
* При распаковке для каждой строки используем два char массива максимальная длинна которых O(m), где m - длина самой длинной строки
* При поиске масимального префикса O(m), где m - длина самой длинной строки
*
*
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PackedPrefix {
    private static final char BRACKET_START = '[';
    private static final char BRACKET_END = ']';

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final String[] unpackedStrings = getUnpackedStrings(reader);
            final String longestCommonPrefix = longestCommonPrefix(unpackedStrings);
            System.out.println(longestCommonPrefix);
        }
    }

    static String longestCommonPrefix(String[] array) {
        final int arrayLength = array.length;
        if (arrayLength == 0)
            return "";

        if (arrayLength == 1)
            return array[0];

        Arrays.sort(array);
        final int min = array[0].length();

        final String first = array[0], last = array[arrayLength - 1];

        int i = 0;
        while (i < min && first.charAt(i) == last.charAt(i))
            i++;

        return first.substring(0, i);
    }

    private static String[] getUnpackedStrings(final BufferedReader reader) throws IOException {
        final int n = Integer.parseInt(reader.readLine());

        final String[] unpackedStrings = new String[n];

        for (int i = 0; i < n; i++) {
            final String packedLine = reader.readLine();
            final String unpackedLine = unpack(packedLine);
            unpackedStrings[i] = unpackedLine;
        }
        return unpackedStrings;
    }

    private static String unpack(String packedLine) {
        final StringBuilder resultBuilder = new StringBuilder();
        final StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < packedLine.length(); i++) {
            char symbol = packedLine.charAt(i);

            if (symbol != BRACKET_END) {
                resultBuilder.append(symbol);
            } else {
                while (symbol != BRACKET_START) {
                    symbol = pop(resultBuilder);
                    buffer.append(symbol);
                }
                pop(buffer);
                symbol = pop(resultBuilder);
                int multiplier = Integer.parseInt(String.valueOf(symbol));

                buffer.reverse();

                for (; multiplier > 0; multiplier--) {
                    resultBuilder.append(buffer);
                }
                buffer.setLength(0); // очищаем
            }
        }
        return resultBuilder.toString();
    }

    static char pop(StringBuilder stringBuilder) {
        final int lastIndex = stringBuilder.length() - 1;
        final char last = stringBuilder.charAt(lastIndex);
        stringBuilder.deleteCharAt(lastIndex);
        return last;
    }
}
