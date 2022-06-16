package practicum.sprint_5.final_task;

import java.io.*;
import java.util.ArrayList;

public class Heapsort {

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final Participant[] participantList = getInputArray(reader);
            BinaryHeap heap = new BinaryHeap(participantList);
            for (Participant participant : heap.getSortedHeap()) {
                writer.write(participant.login + "\n");
            }
        }
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


    static class BinaryHeap {
        private static ArrayList<Participant> heap;
        private int size = 0;

        public BinaryHeap(Participant[] participants) {
            heap = new ArrayList<>();
            for (Participant participant : participants) {
                put(participant);
            }
        }

        private void put(Participant participant) {
            size++;
            heap.add(participant);
            siftUp(size - 1);
        }

        public static void siftUp(int index) {
            if (index == 0) return;
            int headPosition = (index - 1) / 2;
            if (heap.get(index).isRatingUpper(heap.get(headPosition))) {
                swap(headPosition, index);
                siftUp(headPosition);
            }
        }

        private static void swap(int index1, int index2) {
            final Participant temp = heap.get(index1);
            heap.set(index1, heap.get(index2));
            heap.set(index2, temp);
        }

        public ArrayList<Participant> getSortedHeap() {
            return heap;
        }
    }

    static class Participant {
        public String login;
        public int countOfTasks;
        public int fine;

        public Participant(String login, int countOfTasks, int fine) {
            this.login = login;
            this.countOfTasks = countOfTasks;
            this.fine = fine;
        }

        public boolean isRatingUpper(Participant participant) {
            if (participant.countOfTasks == countOfTasks) {
                if (participant.fine == fine) {
                    return login.compareTo(participant.login) < 0;
                }
                return fine < participant.fine;
            }
            return participant.countOfTasks < countOfTasks;
        }
    }
}
