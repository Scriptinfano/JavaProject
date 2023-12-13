package testpack;


import arrayutil.ArrayUtil;

import java.util.Arrays;

import static arrayutil.ArrayUtil.swap;

public class TestAnyThing {
    public static void main(String[] args) {
        int[] arr = ArrayUtil.randomIntArrayWithoutDuplicate(10, 1, 100);
        System.out.println(Arrays.toString(arr));
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr, int n) {
        n--;
        if (n > 0) {
            insertSort(arr, n);
            int a = arr[n];
            int k = n - 1;
            while (k >= 0 && arr[k] > a) {
                arr[k + 1] = arr[k];
                k--;
            }
            arr[k + 1] = a;
        }
    }


    public static int acman(int m, int n) {
        int acm;
        if (m == 0)
            acm = n + 1;
        else if (n == 0)
            acm = acman(m - 1, 1);
        else
            acm = acman(m - 1, acman(m, n - 1));
        return acm;
    }

    public static int q(int n, int m) {
        int p;
        if (n < 1 || m < 1) p = 0;
        else if (n == 1 || m == 1) p = 1;
        else if (n <= m) p = q(n, n - 1) + 1;
        else p = q(n, m - 1) + q(n - m, m);
        return p;
    }

    public static void mergeSort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    public static void merge(int[] arr, int low, int mid, int high) {
        int i = low, j = mid + 1;
        int x = 0;
        int[] new_arr = new int[high - low + 1];
        while (i <= mid && j <= high) {
            if (arr[i] <= arr[j])
                new_arr[x++] = arr[i++];
            else new_arr[x++] = arr[j++];
        }
        while (i <= mid) {
            new_arr[x++] = arr[i++];
        }
        while (j <= high) {
            new_arr[x++] = arr[j++];
        }
        x = 0;
        for (int k = low; k < high + 1; k++) {
            arr[k] = new_arr[x++];
        }
    }

    public static int split(int[] arr, int low, int high) {
        int i = low;
        int x = arr[low];
        for (int k = low + 1; k <= high; k++) {
            if (arr[k] <= x) {
                i++;
                if (i != k)
                    swap(arr, i, k);
            }
        }
        swap(arr, low, i);
        return i;
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int k = split(arr, low, high);
            quickSort(arr, low, k - 1);
            quickSort(arr, k + 1, high);
        }
    }
}

