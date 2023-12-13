package DataStructure.heap;

import static arrayutil.ArrayUtil.swap;

public class Heap {
    public static void main(String[] args) {

    }

    public static void delete(int[] arr, int i) {
        int x = arr[i];
        arr[i] = arr[arr.length - 1];
        if (arr[i] > x) {
            siftUp(arr, i);
        } else sift_down(arr, i);
    }

    public static void sift_down(int[] arr, int i) {
        boolean done = false;
        while (!done) {
            int leftChild = 2 * i;
            int rightChild = 2 * i + 1;
            int bigger;
            if (leftChild < arr.length && rightChild < arr.length) {
                bigger = arr[leftChild] > arr[rightChild] ? leftChild : rightChild;
            } else break;
            // 如果最小值节点不是当前节点，则交换它们，并递归调用堆化下移操作
            if (arr[i] < arr[bigger]) {
                swap(arr, i, bigger);
                i = bigger;
            } else done = true;
        }
    }

    public static void siftUp(int[] arr, int i) {
        boolean done = false;
        i++;
        while (!done && i != 1) {
            if (arr[i - 1] > arr[(i / 2) - 1])
                swap(arr, i - 1, (i / 2) - 1);
            else done = true;
            i = (i / 2);
        }
    }

    public static int[] insert(int[] arr, int x) {
        int[] temp = arr;
        arr = new int[temp.length + 1];
        System.arraycopy(temp, 0, arr, 0, temp.length);
        arr[arr.length - 1] = x;
        siftUp(arr, arr.length);
        return arr;
    }

    public static int[] createHeap(int[] arr) {
        int[] heap = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            heap = insert(heap, arr[i]);
        return heap;
    }
}
