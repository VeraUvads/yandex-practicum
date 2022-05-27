package practicum.sprint_4.final_task;

import java.io.*;
import java.util.*;

/*
 * https://contest.yandex.ru/contest/24414/run-report/68628793/
 *
 * -- ПРИНЦИП РАБОТЫ --
 *
 * В задаче необходимо по запросу найти релевантность документа.
 *
 * Для этого нам нужно построить поисковый индекс для документов.
 * Создаем хэш таблицу filesWordList где ключ - слово, значение - хэш таблица (для которой ключ - номер файла, значение - количество вхождений слова в файл).
 *
 * Чтобы заполнить таблицу, для каждого слова в каждом документе ведем счет вхождений слова в файл. По итогу получим подобие такой структуры:
 * Слово1: {
 *      номер_файла: количтво_совпадений_слова_в_файле
 *      ...
 * },
 * Слово2: {
 *      ...
 * }
 *
 * Когда таблица заполнена начинаем работу с запросами.
 * Для каждого запроса заводим счетчик- хэш таблицу, где ключ- номер документа в котором слово встречается, а значение -
 * количество вхождений всех слов запроса в документ.
 *
 * Для каждого уникального слова в запросе складываем значения вычисленные в filesWordList для каждого слова.
 * После этого полученный счетчик нужно отфильтровать по условию:
 *
 * "Сортировка документов на выдаче производится по убыванию релевантности. Если релевантности документов совпадают —
 * то по возрастанию их порядковых номеров в базе"
 *
 * Для этого я создаю объект у которого реализую интерфейс Comparable под данные условия.
 * Маппингом формируем из мапы список данных объектов, сортируем и выводим 5 первых номеров файла.
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Задача состояла в том чтобы найти релевантность документа. Я решила эту задачу с помощью "поискового индекса".
 * Отображение, которое каждому объекту ставит в соответствие его расположение, называют «поисковым индексом».
 * Для каждого слова в запросе, хеш-таблица должна найти его позицию. Значит, нужно предварительно добавить туда все возможные слова из документов.
 * А если подстроки в хеш-таблице не оказалось, получается, и в документе её нет.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 *  Время затрачиваемое на построение поиского индекса при n - количество документов и m - максимальное количество слов в документе
 *  будет составлять O(n*m), и в худшем случае когда эти значения сопоставимо велики == O(n2)
 *
 *  Время затрачиваемое на обработку запроса при n - количество запросов и m - максимальное количество уникольных слов в запросе будет также составлять
 *  O(n*m), и также в худшем случае когда эти значения сопоставимо велики == O(n2)
 *
 * Тут описаны худшие случаи, на практике количество слов в строке бывает близко к количеству файлов/запросов, поэтому чаще
 * всего эти алгоритмы будут работать за O(n).
 *
 *-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Память затрачиваемая на построение поиского индекса выделяется за O(1) и линейно пополняется в зависимости от количества уникальных слов (n),
 * поэтому нужно памяти O(n).
 * Аналогично для запросов.
 *
 */


public class SearchSystem {
    final static HashMap<String, HashMap<Integer, Integer>> filesWordList = new HashMap<>();
    private static final int BORDER_STEP = 1;
    private static final int FIRST_MATCH_VALUE = 1;
    private static final int MAX_VALUE = 5;
    private static final int FIRST_POSITION = 0;

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final int filesCount = readInt(reader);
            for (int fileNumber = FIRST_POSITION; fileNumber < filesCount; fileNumber++) {
                final String[] words = reader.readLine().split(" ");
                fillFilesMap(words, fileNumber);
            }

            final int requestCount = readInt(reader);
            for (int requestNumber = FIRST_POSITION; requestNumber < requestCount; requestNumber++) {
                final Set<String> wordsInRequest = new HashSet<>(Arrays.asList(reader.readLine().split(" ")));
                final HashMap<Integer, Integer> matches = findMatchAmount(wordsInRequest);
                sortAndPrintResult(matches, writer);
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static void fillFilesMap(String[] words, int fileNumber) {
        for (String word : words) {
            final HashMap<Integer, Integer> map;
            if (filesWordList.containsKey(word)) {
                map = filesWordList.get(word);
            } else {
                map = new HashMap<>();
            }
            if (map.containsKey(fileNumber)) {
                map.put(fileNumber, map.get(fileNumber) + BORDER_STEP);
            } else {
                map.put(fileNumber, FIRST_MATCH_VALUE);
            }
            filesWordList.put(word, map);
        }
    }

    private static HashMap<Integer, Integer> findMatchAmount(Set<String> wordsInRequest) {
        final HashMap<Integer, Integer> counter = new HashMap<>();
        for (final String word : wordsInRequest) {
            final HashMap<Integer, Integer> map = filesWordList.get(word);
            if (map == null) {
                continue;
            }
            for (final Integer fileNumber : map.keySet()) {
                if (counter.containsKey(fileNumber)) {
                    counter.put(fileNumber, counter.get(fileNumber) + map.get(fileNumber));
                } else {
                    counter.put(fileNumber, map.get(fileNumber));
                }
            }
        }
        return counter;
    }

    private static void sortAndPrintResult(HashMap<Integer, Integer> counter, BufferedWriter writer) throws IOException {
        counter.entrySet().stream()
                .map(entry -> new Node(entry.getKey() + BORDER_STEP, entry.getValue()))
                .sorted()
                .limit(MAX_VALUE)
                .forEach(entry -> {
                    try {
                        writer.write((entry.fileNumber) + " ");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        writer.write("\n");
    }


    private static class Node implements Comparable {
        public int fileNumber;
        public int wordMatchesCount;

        public Node(int fileNumber, int wordMatchesCount) {
            this.fileNumber = fileNumber;
            this.wordMatchesCount = wordMatchesCount;
        }

        @Override
        public int compareTo(Object o) {
            final int wordMatchesComparing = ((Node) o).wordMatchesCount - wordMatchesCount;
            if (wordMatchesComparing == 0) {
                return fileNumber - ((Node) o).fileNumber;
            }
            return wordMatchesComparing;
        }
    }
}