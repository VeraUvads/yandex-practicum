package practicum.sprint_2;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class ListedQueueSolution {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int commandsCount = scanner.nextInt();
        ListedQueueSolution.MyListedQueue myListedQueue = new ListedQueueSolution.MyListedQueue();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.contains("get")) {
                myListedQueue.get();
            }
            if (line.contains("size")) {
                myListedQueue.getSize();
            }
            if (line.contains("put")) {
                int x = Integer.parseInt(line.replace("put ", ""));
                myListedQueue.put(x);
            }
        }
    }

    public static class MyListedQueue {

        private static final LinkedList<Integer> list = new LinkedList<>();
        private static int head = 0;
        private static int tail = 0;
        private static int size = 0;

        public void put(int x) {
                list.add(tail, x);
                size++;
                tail++;
        }

        public void get() {
            if (size == 0) {
                System.out.println("error");
            } else {
                System.out.println(list.get(head));
                head++;
                size--;
            }
        }

        public void getSize() {
            System.out.println(size);
        }
    }
}
