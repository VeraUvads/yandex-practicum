package practicum.sprint_2.final_tasks;

import java.io.*;

/*
 * https://contest.yandex.ru/contest/22781/run-report/67728981/
 *
 * -- ПРИНЦИП РАБОТЫ --
 * При добавлении элемента в конец списка:
 * Помещаем в массив на место хвоста списка элемент. Хвост должен сдвинуться влево, поэтому добавляем 1 и берем число по модулю.
 *
 * При вытаскивании элемента из начала списка:
 * Вытаскиваем элемент находящийся на месте головы списка. Мы не должны больше учитывать этот элемент в нашем списке,
 * поэтому сдвигаем голову вправо. Для этого добавляем 1 и берем число по модулю.
 *
 * При добавлении элемента в начало списка:
 * На месте головы в данный момент находится элемент, который нельзя перрерзаписывать. Поэтому прежде чем добавить число в массив,
 * нужно сдвинуть индекс головы влево. Для этого вычитаем 1, добавляем длинну массива и берем число по модулю. Мы добавляем  длинну
 * массива, чтобы при вычитании индекс не уходил в отрицательное значение, а переходил на конец списка.
 *
 * При вытаскивании элемента из конца списка.
 * Хвост в данный момент ссылается на пустой элемент (на то место, куда мы должны записать следующий элемент). Поэтому нам сначала нужно
 * перезаписать индекс хвоста по аналогии с добавлением в начало списка: вычитаем 1, добавляем длинну массива и берем число по модулю.
 * И выводим элемент с этим индексом. При следующем добавлении элемента в конец списка он просто перезапишется.
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 *
 * Решение реализовано по принципу кольцевого буфера. Мы не удаляем эллементы, мы перезаписываем индексы начала и конца списка.
 *
 * При попытке добавить элемент в список (начало или конец) происходит проверка, не ссылаются ли хвост и голова на тот же индекс,
 * это не позволит нам добавить элемент если максимальная длинна массива превышена.
 *
 * При попытке вытащить элемент из списка, происходит проверка не пуст ли массив. Это не позволит нам обратиться к некорректному элекменту.
 * Для этого реализован счетчик size который уменьшается или увеличивается в зависимости от манипуляций с массивом.
 *
 * Индекс никогда не выйдет за пределы массива, поскольку мы всегда берем число по модулю.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 *
 * В каждой операции происходит следующее:
 * 1) Сравнение на корректность
 * 2) Добавление эллемента в массив за О(1) или вывод числа.
 * 3) Перезапись одного из индексов
 * 4) Перезапись size
 * Каждая операция выполняется за константное время О(1), поскольку она  не зависит от количества входных данных.
 *
 *-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Зависит от длинны числа maxSize.
 * Массив, содержащий n элементов, занимает O(n) памяти. Элементы head, tail, size, maxSize занимают О(1) каждый.
 * O(n) + O(1)+ O(1)+ O(1)+ O(1) = O(n)
 *
 * */

public class DequeSolution {
    private static final String POP_FRONT = "pop_front";
    private static final String POP_BACK = "pop_back";
    private static final String PUSH_BACK = "push_back";
    private static final String PUSH_FRONT = "push_front";
    private static final String SPACE = " ";
    private static final String EMPTY_STRING = "";
    private static final String ERROR = "error";


    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int commandsCount = readInt(reader);
            final int maxSize = readInt(reader);
            final DequeSolution.Deque deque = new DequeSolution.Deque(maxSize);
            for (int i = 0; i < commandsCount; i++) {
                final String line = reader.readLine();
                if (line.contains(POP_FRONT)) {
                    deque.popFront();
                }
                if (line.contains(POP_BACK)) {
                    deque.popBack();
                }
                if (line.contains(PUSH_BACK)) {
                    final int number = Integer.parseInt(line.replace(PUSH_BACK + SPACE, EMPTY_STRING));
                    deque.pushBack(number);
                }
                if (line.contains(PUSH_FRONT)) {
                    final int number = Integer.parseInt(line.replace(PUSH_FRONT + SPACE, EMPTY_STRING));
                    deque.pushFront(number);
                }
            }
        }
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    public static class Deque {

        private int[] list;
        private int head = 0;
        private int tail = 0;
        private int size = 0;
        private static int max_size = 0;

        public Deque(int max_size) {
            list = new int[max_size];
            Deque.max_size = max_size;
        }

        public void pushBack(int number) {
            if (tail == head && size != 0) {
                System.out.println(ERROR);
            } else {
                list[tail] = number;
                tail = (tail + 1) % max_size;
                size++;
            }
        }

        public void pushFront(int number) {
            if (tail == head && size != 0) {
                System.out.println(ERROR);
            } else {
                head = (head - 1 + max_size) % max_size;
                list[head] = number;
                size++;
            }
        }

        public void popBack() {
            if (size == 0) {
                System.out.println(ERROR);
            } else {
                tail = (tail - 1 + max_size) % max_size;
                System.out.println(list[tail]);
                size--;
            }
        }

        public void popFront() {
            if (size == 0) {
                System.out.println(ERROR);
            } else {
                System.out.println(list[head]);
                head = (head + 1) % max_size;
                size--;
            }
        }
    }

}
