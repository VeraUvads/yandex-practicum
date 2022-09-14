package practicum.sprint_7.final_task;

/*  https://contest.yandex.ru/contest/25597/run-report/70107771/
 *
 * -- ПРИНЦИП РАБОТЫ --
 *
 * Имея две строки необходимо найти расстояние по Левинштейну между нимпи. Расстоянием Левенштейна между двумя строками s и t называется количество
 * атомарных изменений, с помощью которых можно одну строку превратить в другую.
 * Под атомарными изменениями подразумеваются: удаление одного символа, вставка одного символа, замена одного символа на другой.
 *
 * Решая эту задачу динамическим программированием нам необходимо ответить на следующие вопросы:
 * 1) Что будет находиться в dp[i][j]?
 * Там будет находиться расстояние по Левинштейну при длинне i строки s и при длинне j строки t.
 *
 * 2) Базовый случай
 * dp[0][0] == 0 - когда у нас обе строки с нулевой длинной, нам необходимо сделать 0 преобразований.
 *
 * 3) Как будет идти расчет
 *
 * При проходе по строкам у нас может быть 4 варианта как поступить:
 *   1. Оставить все как есть (для этого s[i]==t[j])
 *   2. Вычеркнуть s[i]
 *   3. Добавить к строке t[j]
 *   4. Заменить t[j] на s[i]
 *
 *   1. Если мы оставляем все как есть (строка не требует преобразований), то тогда расстояние по Левинштейну для dp[i][j]
 * будет равно расстоянию на предыдущей длинне строки dp[i-1][j-1].
 *   2. Если вычеркнуть s[i] то dp[i][j] = dp[i-1][j] + 1
 *   3. Если добавить t[j] to dp[i][j] = dp[i][j-1] + 1
 *   4. Если заменить, то dp[i][j] = dp[i-1][j-1] + 1
 *
 * Для случаев 2, 3 и 4 возникает спорная ситуация - какую операцию в итоге применить? Так как нам надо найти наименьшее расстояние, то
 * нам надо взять минимальное из получившихся значений и записать его в dp[i][j].
 *
 * Так же необходимо учесть что для
 * dp[i][0] = j
 * dp[0][j] = i
 * Поскольку нам нужно добавить столько букв в пустую строку, сколько содерживатся в другой строке на этот период.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Временная сложность равна O(s*t), и при s==t будет квадратичной O(n^2)
 *
 * П
 *
 *
 **/

import java.io.*;

public class LevenshteinDistance {

    private static final int STEP = 1;
    private static final int START = 0;

    private static int[][] matrix;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));) {
            final char[] s = getCharArray(reader);
            final char[] t = getCharArray(reader);
            matrix = new int[s.length + 1][t.length + 1];
            fillMatrix(s, t);
            System.out.print(matrix[s.length][t.length]);
        }
    }

    private static void fillMatrix(char[] s, char[] t) {
        for (int i = START; i <= s.length; i++) {
            for (int j = START; j <= t.length; j++) {
                if (i == START) {
                    matrix[i][j] = j;
                } else if (j == START) {
                    matrix[i][j] = i;
                } else if (s[i - STEP] == t[j - STEP]) {
                    matrix[i][j] = matrix[i - STEP][j - STEP];
                } else {
                    final int up = matrix[i - STEP][j];
                    final int left = matrix[i][j - STEP];
                    final int upLeft = matrix[i - STEP][j - STEP];
                    matrix[i][j] = min(up, left, upLeft) + STEP;
                }
            }
        }
    }

    public static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    private static char[] getCharArray(final BufferedReader reader) throws IOException {
        final String line = reader.readLine();
        return line.toCharArray();
    }
}
