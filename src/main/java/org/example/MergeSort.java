package org.example;

public class MergeSort {

    private static final int CUTOFF = 16;

    public static void sort(int[] arr) {
        int[] buffer = new int[arr.length];
        Metrics.incAllocations();
        sort(arr, buffer, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int[] buffer, int left, int right) {
        if (right - left <= CUTOFF) {
            insertionSort(arr, left, right);
            return;
        }

        Metrics.enterRecursion();

        int mid = (left + right) / 2;

        sort(arr, buffer, left, mid);
        sort(arr, buffer, mid + 1, right);


        Metrics.incComparisons();
        if (arr[mid] <= arr[mid + 1]) {
            Metrics.exitRecursion();
            return;
        }

        merge(arr, buffer, left, mid, right);

        Metrics.exitRecursion();
    }

    private static void merge(int[] arr, int[] buffer, int left, int mid, int right) {

        System.arraycopy(arr, left, buffer, left, right - left + 1);
        Metrics.incAllocations();

        int i = left, j = mid + 1;

        for (int k = left; k <= right; k++) {
            if (i > mid) {
                arr[k] = buffer[j++];
            } else if (j > right) {
                arr[k] = buffer[i++];
            } else {
                Metrics.incComparisons();
                if (buffer[i] <= buffer[j]) {
                    arr[k] = buffer[i++];
                } else {
                    arr[k] = buffer[j++];
                }
            }
        }
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left) {
                Metrics.incComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else break;
            }
            arr[j + 1] = key;
        }
    }
}
