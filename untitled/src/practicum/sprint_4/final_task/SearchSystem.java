package practicum.sprint_4.final_task;

import java.io.*;
import java.util.*;

public class SearchSystem {
    final static HashMap<String, int[]> filesWordList = new HashMap<>();

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final int filesCount = readInt(reader);
            fillFilesMap(filesCount, reader);
            findMatchAmount(reader, filesCount, writer);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static void fillFilesMap(int filesCount, BufferedReader reader) throws IOException {
        for (int i = 0; i < filesCount; i++) {
            String[] words = reader.readLine().split(" ");
            for (String word : words) {
                int[] list;
                if (filesWordList.containsKey(word)) {
                    list = filesWordList.get(word);
                    list[i]++;
                } else {
                    list = new int[filesCount];
                    list[i] = 1;
                }
                filesWordList.put(word, list);
            }
        }
    }

    private static void findMatchAmount(BufferedReader reader, int filesCount, BufferedWriter writer) throws IOException {
        final int requestCount = readInt(reader);
        for (int i = 0; i < requestCount; i++) {
//            ArrayList<Node> counter = new ArrayList<>();
            Map<Integer, Integer> counter = new HashMap<>();
            Set<String> wordsInRequest = new HashSet<>(Arrays.asList(reader.readLine().split(" ")));
            for (String word : wordsInRequest) {
                int[] list = filesWordList.get(word);
                if (list != null) {
                    for (int j = 0; j < filesCount; j++) {
                        if (counter.containsKey(j)) {
                            int count = counter.get(j) + list[j];
                            counter.put(j, count);
                        } else {
                            counter.put(j, list[j]);
                        }
                        //                        counter.add(counter);
//                        Node count = counter.get(j);
//                        if (list[j] != 0 && count == null) {
//                            count = new Node(j, list[j]);
//                        } else {
//                            count.wordMatchesCount += list[j];
//                        }
//                        counter.set(j, count);
                    }
                }
            }
            sortAndPrintResultForList(counter, writer);
        }
    }

    private static void sortAndPrintResultForList(Map<Integer, Integer> counter, BufferedWriter writer) throws IOException {
//        List<Node> result = counter.stream()
//                .filter(node -> node.wordMatchesCount != 0)
//                .sorted((old, fresh) -> fresh.wordMatchesCount - old.wordMatchesCount)
//                .limit(5)
//                .collect(Collectors.toList());
//        counter.values().stream().sorted().collect();
//        for (Node node : result) {
//            writer.write(node.fileNumber + 1 + " ");
//        }
//        int resultPrintedCounter = 0;
//
//        List<Integer> values = counter.values().stream().collect(Collectors.toList());
//        List<Integer> keys = counter.keySet().stream().collect(Collectors.toList());
        counter.entrySet().stream()
                .filter(entry -> entry.getValue() != 0)
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .forEach(result -> {
                    try {
                        writer.write((result.getKey() + 1) + " ");
//                        writer.write((result.getValue()) +" ");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
//        for (int i = values.size() - 1; resultPrintedCounter < 5 && i >= 0; i--) {
//            if (values.get(i) != 0) {
//                int fileNumber = keys.get(i) + 1;
//                writer.write(fileNumber + " ");
//                resultPrintedCounter++;
//            }
//        }
        writer.write("\n");
    }


    private static class Node {
        public int fileNumber;
        public int wordMatchesCount;

        public Node(int fileNumber, int wordMatchesCount) {
            this.fileNumber = fileNumber;
            this.wordMatchesCount = wordMatchesCount;
        }
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
