package practicum.sprint_3.final_task;

import java.io.*;
/*
 * https://contest.yandex.ru/contest/23815/run-report/68431635/
 *
 * -- ПРИНЦИП РАБОТЫ --
 * Для реализации задачи использует сортировка Quick-In-Place.
 *
 * 1) Чтобы не использовать дополнительную память при сортировке, инициализируем массив как поле класса (in-place)
 * 2) Выбираем опорный элемент (я выбрала  элемент  посередине)
 * 3) Заводим два указателя left и right. Затем будем двигать левый указатель вправо до тех пор, пока он указывает на элемент, меньший опорного,
 * и  аналогично для правого.
 * 4) Меняем места на указателях местами с помощью функции swap
 * 5) Рекурсивно вызываем функцию сортировки передавая левый и правый указатель
 *
 * Сравнение происходят по трем полям:  Если количество решенных задач равно, и штрафы равны, то возвращаем лексикографическое сравнение.
 * Если количество решенных задач равно возвращаем сравнение по штрафам. Иначе возвращаем сравнение по количеству задач.
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Задача состояла в том чтобы не используя память больше чем O(n) отсортировать массив за временную сложность O(log(n)).
 * Quick-sort позволяет достичь скорости O(log(n)), но дает нам ограничения по памяти, если мы будем рекусировно передавать массив.
 * Поэтому если мы вынесем массив как поле класса используя принцип in-place, мы обойдем это ограничение, и сможем корректно и быстро
 * отсортировать массив.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Поскольку тут используется быстрая сортировка, алгоритм на практике работает за O(log(n)). В худшем случае он будет работать за O(n2)
 * если опорный элемент выбран неудачно.
 *
 *-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 *  * Массив, содержащий n элементов, занимает O(n) памяти. Элементы middle, pivot, left, resultInLeft занимают О(1) каждый.
 * O(n) + O(1)+ O(1)+ O(1)+ O(1) = O(n)
 *
 * */

public class QuickSortInPlace {
    private static Participant[] participantList;

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            participantList = getInputArray(reader);
            quickSort(0, participantList.length - 1);
            for (final Participant participant : participantList) {
                writer.write(participant.name + "\n");
            }
        }
    }

    private static void quickSort(final int low, final int high) {
        int left = low;
        int right = high;
        final int middle = low + (high - low) / 2;
        final Participant pivot = participantList[middle];

        while (left <= right) {

            while (participantList[left].isRatingUpper(pivot)) {
                left++;
            }
            while (pivot.isRatingUpper(participantList[right])) {
                right--;
            }
            if (left <= right) {
                swap(left, right);
                left++;
                right--;
            }
        }
        if (low < right) {
            quickSort(low, right);
        }

        if (left < high) {
            quickSort(left, high);
        }
    }

    private static void swap(int index1, int index2) {
        final Participant temp = participantList[index1];
        participantList[index1] = participantList[index2];
        participantList[index2] = temp;
    }


    private static Participant[] getInputArray(final BufferedReader reader) throws IOException {
        final int n = Integer.parseInt(reader.readLine());
        final Participant[] list = new Participant[n];

        for (int i = 0; i < n; i++) {
            list[i] = parseParticipant(reader.readLine());
        }
        return list;
    }

    private static Participant parseParticipant(String nextToken) {
        final String[] data = nextToken.split(" ");
        return new Participant(
                data[0],
                Integer.parseInt(data[1]),
                Integer.parseInt(data[2]));
    }

    static class Participant {
        public String name;
        public int countOfTasks;
        public int fine;

        public Participant(String name, int countOfTasks, int fine) {
            this.name = name;
            this.countOfTasks = countOfTasks;
            this.fine = fine;
        }

        public boolean isRatingUpper(Participant participant) {
            if (participant.countOfTasks == countOfTasks) {
                if (participant.fine == fine) {
                    return name.compareTo(participant.name) < 0;
                }
                return fine < participant.fine;
            }
            return participant.countOfTasks < countOfTasks;
        }
    }

}
