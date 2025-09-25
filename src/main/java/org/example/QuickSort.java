package org.example;

import java.util.Random;

public class QuickSort {

    private static final Random rand = new Random();

    public static void sort(int[] arr) {
        quicksort(arr, 0, arr.length - 1);
    }

    private static void quicksort(int[] arr, int low, int high) {
        while (low < high) {

            int pivotIndex = low + rand.nextInt(high - low + 1);
            int pivot = arr[pivotIndex];
            swap(arr, pivotIndex, high);

            int p = partition(arr, low, high, pivot);


            if (p - low < high - p) {
                quicksort(arr, low, p - 1);
                low = p + 1;
            } else {
                quicksort(arr, p + 1, high);
                high = p - 1;
            }
        }
    }

    private static int partition(int[] arr, int low, int high, int pivot) {
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
