package practicum.sprint_5;

public class SiftUp {

    public static int siftUp(int[] heap, int idx) {
        if (idx == 1) return idx;
        int headPosition = idx / 2;
        if (heap[idx] > heap[headPosition]) {
            swap(heap, headPosition, idx);
            return siftUp(heap, headPosition);
        } else {
            return idx;
        }
    }

    private static void swap(int[] heap, int index1, int index2) {
        final int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    private static void test() {
        int[] sample = {-1, 12, 6, 8, 3, 15, 7};
        System.out.println(siftUp(sample, 5) + " == 1");
    }

    public static void main(String[] args) {
        test();
    }
}