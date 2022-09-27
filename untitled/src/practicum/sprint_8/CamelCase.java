package practicum.sprint_8;

import java.io.*;
import java.util.*;

public class CamelCase { // не закончено

    private static Node root;
    private static BufferedWriter writer;
    private static LinkedHashSet<String> result;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out))) {
            root = new Node();
            writer = bufferedWriter;
            result = new LinkedHashSet<>();
            fillPrefixTree(reader);
            findResult(reader);
            for (String word : result) {
                writer.write(word + "\n");
            }
        }
    }

    private static void findResult(BufferedReader reader) throws IOException {
        int patternCount = Integer.parseInt(reader.readLine());
        for (int i = 0; i < patternCount; i++) {
            final String line = reader.readLine();
            root.printAllIfContain(line, 0);
        }
    }

    private static void fillPrefixTree(BufferedReader reader) throws IOException {
        int textCount = Integer.parseInt(reader.readLine());
        for (int i = 0; i < textCount; i++) {
            String line = reader.readLine();
            root.addChildren(line, 0);
        }
    }

    private static class Node {
        String value;
        List<Node> children = new ArrayList<>();
        ArrayList<String> words = new ArrayList<>();


        public Node(String sentence, int index) {
            if (isOutOfBounds(sentence, index)) return;
            value = String.valueOf(sentence.charAt(index));
            addChildren(sentence, ++index);
        }

        private boolean isOutOfBounds(String sentence, int index) {
            return sentence.isEmpty() || index >= sentence.length();
        }

        public Node() {
            value = "";
        }

        private void addChildren(String sentence, int index) {
            if (isOutOfBounds(sentence, index)) return;
            char letter = sentence.charAt(index);
            while (!isUpperCase(letter)) {
                index++;
                if (isOutOfBounds(sentence, index)) {
                    words.add(sentence);
                    return;
                }
                letter = sentence.charAt(index);
            }

            Node node = getNodeByValue(letter);
            if (node == null) {
                children.add(new Node(sentence, index));
            } else {
                node.addChildren(sentence, ++index);
            }
        }

        private void printAllIfContain(String pattern, int index) {
            if (index >= pattern.length()) {
                printAll();
                return;
            }
            char letter = pattern.charAt(index);
            Node node = getNodeByValue(letter);
            if (node != null) {
                node.printAllIfContain(pattern, ++index);
                node.printWords();
            }
        }

        private void printAll() {
            for (Node child : children) {
                child.printAll();
                child.printWords();
            }
        }

        private void printWords() {
//            try {
//                for (String word : words) {
//                    writer.write(word + "\n");
//                }
            result.addAll(words);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }

        private boolean isUpperCase(char letter) {
            return letter >= 'A' && letter <= 'Z';
        }

        private Node getNodeByValue(char value) {
            for (Node node : children) {
                if (Objects.equals(node.value, String.valueOf(value))) {
                    return node;
                }
            }
            return null;
        }

    }
}
