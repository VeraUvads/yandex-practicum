package practicum.sprint_4.final_task;

import java.io.*;
import java.util.Objects;

/*
* https://contest.yandex.ru/contest/24414/run-report/68629112/
*
 * -- ПРИНЦИП РАБОТЫ --
 *
 * Задача: реализовать хэш таблицу и разрешать коллизии с помощью метода цепочек или метода открытой адресации.
 * В данном решении реализовано разрешение коллизий с помощью метода открытой адресации.
 *
 * Реализация put:
 * Я выбрала стратегию линейного пробирования.
 * Стратегия пробирования — это способ выбрать следующую корзину для выполнения операции (вставки или поиска ключа) и сделать шаг в неё,
 * если текущая корзина занята неподходящим объектом (не тем, который мы ищем, при поиске и любым — при вставке).
 * Стратегия линейного пробирования выражается формулой:
 *  h(k, i) = h(k) + ih(k,i)=h(k)+i
 *  Читать эту формулу следует так: индекс корзины равен сумме хеш-функции от ключа h(k) и номера шага пробирования i.
 *
 * Реализация get:
 * Вычисляем индекс корзины, используя хеш-функцию: x = h(k)%mod
 * Если искомый элемент — в этой корзине, то поиск успешно окончен. Если корзина пустая — поиск останавливается.
 * Иначе продолжаем искать в следующих по порядку корзинах
 *
 * Реализация delete:
 * Если мы не нашли значение которое нужно удалить выводим NONE, если нашли - выводим значение и помечаем как DELETED.
 * Нельзя просто удалить значение, потому что поиск элемента ведется ровно до тех пор пока мы не встретим пустую в корзину.
 * Поэтому удаленный индекс должен быть помечен как удаленный, а не как пустой.
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Для того чтобы операции в хэш таблице были эффективны должна быть хорошая лавинность. Много DELETED элементов замедляют функцию, и при
 * определнном количнестве нужно будет делать рехеширование для того чтобы сохранять эффективность, но это долгая операция, которую
 * в задаче сказано не реализовывать.
 * В данном случае стратегия линейного пробирования реализует достаточную для входных данных лавинность, так как операции выполняются за O(1).
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * В среднем все операции выполняются за O(1), за исключением случаев когда нам нужно разрешать коллизии. Рехеширование в задаче реализовано не было,
 * поэтому пересчет списка не происходит.
 *
 *-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Память затрачиваемая на реализацию хэш таблицы составляет O(n), поскольку мы выделяем память только под размер элементов, и не более.
 *
* */

public class HashTableSolution {
    private static final String GET = "get";
    private static final String PUT = "put";
    private static final String DELETE = "delete";

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final int commandsCount = readInt(reader);
            final HashTable hashTable = new HashTable(commandsCount);
            for (int i = 0; i < commandsCount; i++) {
                final String[] line = reader.readLine().split(" ");
                final String command = line[0];
                final String key = line[1];
                if (Objects.equals(command, GET)) {
                    String result = hashTable.get(key);
                    writer.write(result + "\n");
                }
                if (Objects.equals(command, PUT)) {
                    final String value = line[2];
                    hashTable.put(key, value);
                }
                if (Objects.equals(command, DELETE)) {
                    String deletedItem = hashTable.delete(key);
                    writer.write(deletedItem + "\n");
                }
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    public static class HashTable {
        private static final String NONE = "None";
        private static final String DELETED = "deleted";
        private static final int NOT_FOUND_VALUE = -1;
        private static final int PROBING = 11;
        private final Node[] list;
        private final int size;

        HashTable(int size) {
            list = new Node[size];
            this.size = size;
        }

        public void put(String key, String value) {
            int probingStep = 0;
            final Node nodeToPut = new Node(key, value);
            int index = increaseBucketIndex(key, probingStep);

            while (list[index] != null) {
                if (list[index].key.equals(key)) {
                    break;
                }
                probingStep++;
                index = increaseBucketIndex(key, probingStep);
            }
            list[index] = nodeToPut;
        }

        public String get(String key) {
            int index = getIndex(key);
            return getValueByIndex(index);
        }

        public int getIndex(String key) {
            int probingStep = 0;
            int index = increaseBucketIndex(key, probingStep);
            while (list[index] != null) {
                Node item = list[index];
                if (item.key.equals(key)) {
                    return index;
                }
                probingStep++;
                index = increaseBucketIndex(key, probingStep);
            }
            return NOT_FOUND_VALUE;
        }

        private int increaseBucketIndex(String key, int probingStep) {
            return (Long.valueOf(key).hashCode() % size + PROBING * probingStep) % size;
        }

        private String getValueByIndex(int index) {
            if (index == NOT_FOUND_VALUE){
                return NONE;
            } else {
                return list[index].value;
            }
        }

        public String delete(String key) {
            int index = getIndex(key);
            String value = getValueByIndex(index);
            if (index != NOT_FOUND_VALUE) {
                list[index].setDeleted();
            }
            return value;
        }

        private static class Node {
            private String key;
            private String value;

            public Node(String key, String value) {
                this.key = key;
                this.value = value;
            }

            public void setDeleted() {
                this.key = DELETED;
                this.value = DELETED;
            }
        }
    }
}