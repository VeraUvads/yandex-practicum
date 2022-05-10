package practicum.sprint_2.final_tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/*
 * https://contest.yandex.ru/contest/22781/run-report/67728981/
 *
 * -- ПРИНЦИП РАБОТЫ --
 * Идем слева направо по строке, если это число, то записываем его в стек, если это оператор, то вытаскиваем два предыдущих числа,
 * производим операцию в соответствии с предоставленным оператором, и записываем результат в стек.
 *
 * Способ определить является ли символ оператором:
 * У  нас есть регекс, состоящий из всех символов операций. Сравнение по  регексу было бы достаточным, если бы не унарный минус, поэтому мы делаем
 * дополнительную проверку: если после минуса стоит пробел, значит это оператор.
 *
 * Способ сформировать число из строки:
 * Создаем строковую переменную number, и записываем в нее значения до тех пор, пока нам не встретится пробел, или символ не окажется последним
 * в строке.
 *
 * Деление отрицательных чисел:
 * По неизвестным мне причинам так было заявлено в условии, поэтому при делении с одним отрицательным числом правила деления переопределены
 * и действуют по принципу округления вверх.
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Из описания задачи следует:
 * Чем левее оператор, тем раньше он должен быть выполнен.
 * Чем правее число, тем раньше с ним нужно произвести операцию.
 * Стек инвертирует порядок элементов: первые становятся последними, поэтому для хранения чисел используется именно стек.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * При n равному количеству чисел и операторов:
 *
 * Мы проходимся по строке один раз, слева направо. Эта операция занимает О(n).
 * Внутри итерации выполняются операции:
 * 1) сравнения. O(1)
 * 2) записи числа. O(1)
 * 3) вычисления. O(1)
 * 4) добавления в стек. В лучшем случае О(1). В худшем случае О(n/2 +1), когда в массиве закончится память, и нужно будет
 * создать новый и перезаписать туда предыдущие значения. n/2 +1 - потому что в стеке мы храним только числа, а они всегда будут на
 * один больше чем  операторов, тоесть половина +1; Отбросив все константы и коэффициенты это О(n).
 * или
 * 5) выделения памяти под переменные, каждая за О(1), но где-то это происходит n раз, поэтому в худшем случае О(n).

 * Почти все из операций не зависят от количества входных данных, и выполняются за константное время О(1).
 * Операция перезаписи массива будет выполняться не каждый раз, а примерно k раз, поэтому не сделает алгоритм квадратичным,
 * а скорее линейным с доп коэффцициентом.
 * О(n) + О(k*n) + O(n) + O(1)+ O(1) + O(1);
 * Отсюда можно сделать вывод что временная сложность алгоритма равна О(n). Очень надеюсь что я не перемудрила тут с подсчетами.
 *
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Стек, по аналогии с тем что описано выше занимает О(n).
 * Вспомогательные переменные  используют O(1), и пускай где-то это происходит n раз, при очередной итерации предыдущие переменные
 * будут собраны сборщиком мусора, поэтому здесь сложность константная.
 *
 * Поэтому решение займет O(1) + O(n) = О(n)
 *
 */


public class PolishNotation {
    private static final String OPERATOR_REGEX = "*/-+";

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final List<String> data = readList(reader);
            System.out.println(calculate(data));
        }
    }

    private static List<String> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).collect(Collectors.toList());
    }

    private static int calculate(List<String> symbolList) {
        final Stack<Integer> numbersStack = new Stack<>();
        symbolList.forEach(element -> {
                    if (isOperator(element)) {
                        final int first = numbersStack.pop();
                        final int second = numbersStack.pop();
                        final int result = calculate(first, second, element);
                        numbersStack.push(result);
                    } else {
                        numbersStack.push(Integer.parseInt(element));
                    }
                }
        );
        return numbersStack.pop();
    }

    private static int calculate(int first, int second, String operator) {
        switch (operator) {
            case "+":
                return second + first;
            case "-":
                return second - first;
            case "*":
                return second * first;
            case "/":
                return divide(first, second);
        }
        return 0;
    }

    private static int divide(int first, int second) {
        if (first > 0 && second > 0 || first < 0 && second < 0) {
            return second / first;
        } else {
            return (int) Math.floor((double) second / first);
        }
    }

    private static boolean isOperator(String element) {
        return OPERATOR_REGEX.contains(element) &&
                (element.length() == 1);
    }
}
