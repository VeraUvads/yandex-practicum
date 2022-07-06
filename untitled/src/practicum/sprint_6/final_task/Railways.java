package practicum.sprint_6.final_task;

import java.io.*;
import java.util.*;
/* https://contest.yandex.ru/contest/25070/run-report/69319137/
 * -- ПРИНЦИП РАБОТЫ --
 * По условию дано: По дорогам страны X можно двигаться только от города с меньшим номером к городу с большим номером. Значит зацикливание невозможно.
 * Нам нужно определить  - из какого города с дорогой типа B можно добраться в ту же точку дорогой типа R.
 * Попробуем перевернуть направление дорог для типа R. Тогда если мы от точки А доберемся до некоторой точке С по дороге типа R, в случае если
 * неоптимальные дороги существуют, то от точки С мы вернемся в точку A по дороге типа B. И тогда мы впадаем в цикл.
 * Таким образом задача сводится к определению цикла в графе.
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Я использовала следующие статьи
 * https://www.geeksforgeeks.org/detect-cycle-in-a-graph/
 * https://www.baeldung.com/java-graph-has-a-cycle
 * с модернизацией на использование стека вместо рекурсии.
 * Также, нам не было необходимо проходить все точки. Если цикл сущесвует, определить это можно пройдясь по точкам одного из графа.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Сложность равна O(E*V) где |E|— количество рёбер в графе, а |V| — количество вершин.
 *
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Нам необходимо хранить все вершины и список смежных для них вершин.
 * Тоесть для каждой вершины V, все вершины являются смежными равны количеству ребер E.
 * И тогда пространственная сложность равна  O(E*V)
 *
 */

public class Railways {
    private static final int white = 0;
    private static final int grey = 1;
    private static final int black = 2;

    private static final char DIRECT_ROAD = 'R';
    private static final char REVERSED_ROAD = 'B';

    private static Set<Integer> notVisitedReversed;
    static Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
    static int[] colors;

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int cityCount = Integer.parseInt(reader.readLine());
            graph = new HashMap<>();
            notVisitedReversed = new HashSet<>();
            colors = new int[cityCount + 1];
            fillGraph(reader, cityCount);
            if (hasCycle()) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
    }

    public static boolean hasCycle() {
        for (int vertex : notVisitedReversed) {
            if (colors[vertex] != black && hasCycle(vertex)) {
                return true;
            }
        }
        return false;
    }

    private static void fillGraph(BufferedReader reader, int cityCount) throws IOException {
        for (int city = 1; city <= cityCount; city++) {
            final ArrayList<Integer> list = new ArrayList<>();
            graph.put(city, list);
        }
        for (int city = 1; city < cityCount; city++) {
            final char[] line = reader.readLine().toCharArray();
            for (int symbolIndex = 0; symbolIndex < line.length; symbolIndex++) {
                final int to = city + symbolIndex + 1;
                final char roadType = line[symbolIndex];
                if (roadType == DIRECT_ROAD) {
                    graph.get(city).add(to);
                } else if (roadType == REVERSED_ROAD) {
                    notVisitedReversed.add(to);
                    graph.get(to).add(city);
                }
            }
        }
    }

    public static boolean hasCycle(int start) {
        final Stack<Integer> stack = new Stack<>();
        stack.add(start);
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (colors[vertex] == white) {
                colors[vertex] = grey;
                stack.add(vertex);
                for (int neighbor : graph.get(vertex)) {
                    if (colors[neighbor] == white) {
                        stack.add(neighbor);
                    } else if (colors[neighbor] == grey) {
                        return true;
                    }
                }
            } else if (colors[vertex] == grey) {
                colors[vertex] = black;
            }
        }
        return false;
    }

}
