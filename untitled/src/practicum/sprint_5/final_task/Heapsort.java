package practicum.sprint_5.final_task;

import java.io.*;

public class Heapsort {

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final Participant[] participantList = getInputArray(reader);
            BinaryHeap heap = new BinaryHeap();
            heap.sort(participantList);
            for (Participant participant : participantList) {
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

        private static int size = 0;

        public void sort(Participant[] participants) {
            size = participants.length;
            for (int i = participants.length / 2 - 1; i >= 0; i--) {
                siftDown(participants, i);
            }

            for (int i = participants.length - 1; i >= 0; i--) {
                Participant temp = participants[0];
                participants[0] = participants[i];
                participants[i] = temp;
                size = i;
                siftDown(participants, 0);
            }

        }

        public static int siftDown(Participant[] heap, int index) {
            int leftPosition = index * 2 + 1;
            int rightPosition = leftPosition + 1;

            int positionOfMin = index;

            if (leftPosition < size && heap[positionOfMin].isRatingUpper(heap[leftPosition])) {
                positionOfMin = leftPosition;
            }

            if (rightPosition < size && heap[positionOfMin].isRatingUpper(heap[rightPosition])) {
                positionOfMin = rightPosition;
            }

            if (positionOfMin != index) {
                swap(heap, index, positionOfMin);
                return siftDown(heap, positionOfMin);
            } else {
                return index;
            }
        }


        private static void swap(Participant[] heap, int index1, int index2) {
            final Participant temp = heap[index1];
            heap[index1] = heap[index2];
            heap[index2] = temp;
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