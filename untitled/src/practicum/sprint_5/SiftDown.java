package practicum.sprint_5;

public class SiftDown {

    public static int siftDown(int[] heap, int idx) {
        int leftPosition = idx * 2;
        int rightPosition = leftPosition + 1;

        int item = heap[idx];
        int positionOfMax = -1;

        if (leftPosition < heap.length) {
            positionOfMax = leftPosition;
        }

        if (rightPosition < heap.length && heap[positionOfMax] < heap[rightPosition]) {
            positionOfMax = rightPosition;
        }

        if (positionOfMax != -1 && item < heap[positionOfMax]) {
            swap(heap, idx, positionOfMax);
            return siftDown(heap, positionOfMax);
        } else {
            return idx;
        }
    }

    private static void swap(int[] heap, int index1, int index2) {
        final int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }
}
