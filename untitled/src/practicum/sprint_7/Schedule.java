package practicum.sprint_7;


import java.io.*;
import java.util.*;

public class Schedule {

    private static List<Lesson> list;
    private static List<Lesson> result;
    private static Double lastSlot = 0.0;

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            list = getInputArray(reader);
            result = new ArrayList<>();
            countResult();
            writer.write(result.size() + "\n");
            result.forEach(elem -> {
                        try {
                            writer.write(elem.toString() + "\n");
                        } catch (IOException e) {
                        }
                    }
            );
        }
    }

    private static void countResult() {
        Collections.sort(list);
        for (Lesson lesson : list) {
            if (lastSlot <= lesson.start) {
                result.add(lesson);
                lastSlot = lesson.end;
            }
        }
    }

    private static ArrayList<Lesson> getInputArray(final BufferedReader reader) throws IOException {
        final int n = Integer.parseInt(reader.readLine());
        final ArrayList<Lesson> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] line = reader.readLine().split(" ");
            list.add(new Lesson(line));
        }
        return list;
    }

    static class Lesson implements Comparable<Lesson> {
        double start;
        double end;

        Lesson(String[] line) {
            this.start = Double.parseDouble(line[0]);
            this.end = Double.parseDouble(line[1]);
        }

        @Override
        public String toString() {
            return String.valueOf(start).replace(".0", "") + " " + String.valueOf(end).replace(".0", "");
        }

        @Override
        public int compareTo(Lesson lesson) {
            double startComparing = this.start - lesson.start;
            double endComparing = this.end - lesson.end;
            if (endComparing > 0) {
                return 1;
            } else if (endComparing < 0) {
                return -1;
            }
            if (startComparing > 0) {
                return 1;
            } else if (startComparing < 0) {
                return -1;
            }
            return 0;
        }
    }
}
