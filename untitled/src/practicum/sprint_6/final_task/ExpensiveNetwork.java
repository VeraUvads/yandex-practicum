package practicum.sprint_6.final_task;

/*  https://contest.yandex.ru/contest/25070/run-report/69294240/
 *
 * -- ПРИНЦИП РАБОТЫ --
 *
 * Необходимо составить оставное дерево и с максимальным весом.
 * Нам не важно с какой вершины мы начнем поскольку все вершины потом окажутся пройденными.
 * Возьмем первую попавшуюся вершину и добавим в список кандидатов все инцидентные ребра.
 * Выберем из них ребро с наибольшим весом, но конечная вершина которого еще не была посещена. Ребро удовлетворяющее условиям войдет в оставной граф.
 * Пометим его включенным, а инцидентные вершины посещенными.
 * Проделаем те же действия от вершины выбранного ребра, до тех пор пока не посетим все вершины.
 * На каждом шаге добавляем вес выбранного ребра к конечному результату
 *
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Задача решена с помощью алгоритма Прима на очереди с приоритетами. Если хранить рёбра, исходящие из уже собранного
 * подмножества остова в куче с поддержанием минимума,  то выбирать ребро с минимальным весом становится легко.
 * Благодаря приоритетной очереди сложность алгоритма Прима стала O(E * log{V})
 * где |E|— количество рёбер в графе, а |V| — количество вершин.
 *
 *
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Необходимая память для работы:
 * массив notVisited - O(V)
 * массив graph - O(V+E) поскольку он представлен в виде списка смежности
 * https://practicum.yandex.ru/learn/algorithms/courses/7f101a83-9539-4599-b6e8-8645c3f31fad/sprints/33729/topics/45179065-a73b-473d-94d1-24774573f266/lessons/e895a178-7bd3-4549-95a1-c445f8413e83/
 *
 * */

import java.io.*;
import java.util.*;

public class ExpensiveNetwork {
    private static final int FIRST_INDEX = 0;
    private static final int EMPTY_COUNT = 0;

    private static final int PARSE_FROM_INDEX = 0;
    private static final int PARSE_TO_INDEX = 1;
    private static final int PARSE_WEIGHT_INDEX = 2;
    private static final int PARSE_VERTEXES_INDEX = 0;
    private static final int PARSE_EDGES_INDEX = 1;


    private static long weight = 0;
    private static List<Integer> notVisited;
    private static Vertex[] graph;

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final String[] line = reader.readLine().split(" ");
            final int vertexCount = Integer.parseInt(line[PARSE_VERTEXES_INDEX]);
            final int edgeCount = Integer.parseInt(line[PARSE_EDGES_INDEX]);
            graph = new Vertex[vertexCount + 1];
            notVisited = new ArrayList<>();
            if (vertexCount == EMPTY_COUNT) {
                System.out.println(weight);
                return;
            }
            fillGraph(reader, edgeCount, vertexCount);
            bfsGraph();
        }
    }

    private static void bfsGraph() {
        int currentVertexValue = notVisited.remove(FIRST_INDEX);
        final Queue<Edge> candidates = new PriorityQueue<>();
        while (!notVisited.isEmpty()) {
            final Vertex currentVertex = graph[currentVertexValue];
            for (final Edge edge : currentVertex.edges) {
                if (!graph[edge.getTo()].isVisited) {
                    candidates.add(edge);
                }
            }
            Edge currentEdge = candidates.poll();
            while (currentEdge != null && graph[currentEdge.to].isVisited) {
                currentEdge = candidates.poll();
            }
            if (currentEdge == null) {
                break;
            }
            weight += currentEdge.getWeight();
            graph[currentEdge.getTo()].isVisited = true;
            graph[currentEdge.getFrom()].isVisited = true;
            currentVertexValue = currentEdge.getTo();
            notVisited.remove(Integer.valueOf(currentVertexValue));
            notVisited.remove(Integer.valueOf(currentEdge.getFrom()));
        }

        if (notVisited.isEmpty()) {
            System.out.println(weight);
        } else {
            System.out.println("Oops! I did it again");
        }
    }

    private static void fillGraph(BufferedReader reader, int edgeCount, int vertexCount) throws IOException {
        for (int vertex = 1; vertex <= vertexCount; vertex++) {
            if (graph[vertex] == null) {
                graph[vertex] = new Vertex(vertex);
                notVisited.add(vertex);
            }
        }
        for (int i = 0; i < edgeCount; i++) {
            final String[] line = reader.readLine().split(" ");
            final Edge edge = new Edge(line);
            graph[edge.getFrom()].addEdge(edge);
            graph[edge.getTo()].addEdge(edge.reversed());
        }
    }

    static class Edge implements Comparable<Edge> {
        private final int from;
        private final int to;
        private final long weight;

        Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        Edge(String[] line) {
            this.from = Integer.parseInt(line[PARSE_FROM_INDEX]);
            this.to = Integer.parseInt(line[PARSE_TO_INDEX]);
            this.weight = Long.parseLong(line[PARSE_WEIGHT_INDEX]);
        }

        private Edge reversed() {
            return new Edge(to, from, weight);
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }

        public long getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return (int) (edge.weight - this.weight);
        }
    }

    static class Vertex {
        Set<Edge> edges = new HashSet<>();
        int value;
        boolean isVisited = false;

        Vertex(int value) {
            this.value = value;
        }

        public void addEdge(Edge edge) {
            edges.add(edge);
        }
    }
}
