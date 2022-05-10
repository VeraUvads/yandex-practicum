package practicum.sprint_2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class MyQueueSizedSolution {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int commandsCount = scanner.nextInt();
        int maxSize = scanner.nextInt();
        MyQueueSized myQueueSized = new MyQueueSized(maxSize);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.contains("pop")) {
                myQueueSized.pop();
            }
            if (line.contains("size")) {
                myQueueSized.getSize();
            }
            if (line.contains("peek")) {
                myQueueSized.peek();
            }
            if (line.contains("push")) {
                int x = Integer.parseInt(line.replace("push ", ""));
                myQueueSized.push(x);
            }
        }
    }

    public static class MyQueueSized {

        private static int[] list;
        private static int head = 0;
        private static int tail = 0;
        private static int size = 0;
        private static int max_size = 0;

        public MyQueueSized(int max_size) {
            list = new int[max_size];
            MyQueueSized.max_size = max_size;
        }

        public void push(int x) {
            if (tail == head && size != 0) {
                System.out.println("error");
            } else {
                list[tail] = x;
                size++;
                tail = (tail + 1) % max_size;
            }
        }

        public void pop() {
            if (size == 0) {
                System.out.println("None");
            } else {
                System.out.println(list[head]);
                head = (head + 1) % max_size;
                size--;
            }
        }

        public void peek() {
            if (size == 0) {
                System.out.println("None");
            } else {
                System.out.println(list[head]);
            }
        }

        public void getSize() {
            System.out.println(size);
        }
    }

}
