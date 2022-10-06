package practicum.sprint_8.final_task;

/*
 * https://contest.yandex.ru/contest/26133/run-report/71383507/
 *
 * -- ПРИНЦИП РАБОТЫ --
 *
 * Добавим все слова из которых мы хотим составить шпаргалку в префиксное дерево. Создадим булевый массив, в ячейках которого будем хранить
 * значение, определяющее - есть ли слова из которых можно составить шпаргалку к этому индексу.
 * Пройдемся по шпаргалке вложенным циклом. Если мы по слову дошли к терминальному узлу, значит помечаем что можно.
 * Внутренний цикл проводим только если к этому моменту он помечен как true.
 * Для других случае оставляем false.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Построение префиксного дерева: O(n), где n сумма длинн всех строк
 * Прохождение по префиксному дереву: O(m^2), где m - количество символов в шпаргалке
 *
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Мы храним префиксное дерево O(n), где n сумма длинн всех строк
 * и булевый массив O(m), где m - количество символов в шпаргалке
 *
 *
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class CheatSheet {

    private static final String YES = "YES";
    private static final String NO = "NO";


    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final String cheatSheet = reader.readLine();
            final String[] words = getInputArray(reader);
            final Node root = createTree(words);
            if (isCheatSheet(root, cheatSheet)) {
                System.out.println(YES);
            } else {
                System.out.println(NO);
            }
        }
    }

    private static boolean isCheatSheet(Node root, String cheatSheet) {
        final boolean[] dp = new boolean[cheatSheet.length() + 1];
        dp[0] = true;
        for (int i = 0; i < cheatSheet.length(); i++) {
            Node currentNode = root;
            if (!dp[i]) continue;
            for (int j = i; j <= cheatSheet.length(); j++) {
                if (currentNode.isTerminal()) {
                    dp[j] = true;
                }
                if (j == cheatSheet.length()) {
                    break;
                }
                final String symbol = String.valueOf(cheatSheet.charAt(j));
                final Node next = currentNode.getChild(symbol);
                if (next == null) {
                    break;
                }
                currentNode = next;
            }
        }
        return dp[dp.length - 1];
    }

    private static String[] getInputArray(BufferedReader reader) throws IOException {
        final int n = Integer.parseInt(reader.readLine());
        final String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = reader.readLine();
        }
        return words;
    }

    private static Node createTree(String[] words) {
        final Node root = new Node("");
        for (final String word : words) {
            Node currentNode = root;
            for (int index = 0; index < word.length(); index++) {
                final String symbol = String.valueOf(word.charAt(index));
                final Node child = currentNode.getChild(symbol);
                if (child == null) {
                    final Node newChild = new Node(symbol);
                    currentNode.addChild(newChild);
                    currentNode = newChild;
                } else {
                    currentNode = child;
                }
            }
            currentNode.setTerminal(true);
        }
        return root;
    }

    private static class Node {
        private final String value;
        private final ArrayList<Node> children;
        private boolean isTerminal = false;

        public Node(String value) {
            this.value = value;
            this.children = new ArrayList<>();
        }

        private Node getChild(String value) {
            for (Node node : children) {
                if (Objects.equals(node.value, value)) {
                    return node;
                }
            }
            return null;
        }

        private void addChild(Node node) {
            if (!isTerminal && !children.isEmpty()) {
                setTerminal(true);
            }
            children.add(node);
        }

        public boolean isTerminal() {
            return isTerminal;
        }

        public void setTerminal(boolean isTerminal) {
            this.isTerminal = isTerminal;
        }
    }
}
