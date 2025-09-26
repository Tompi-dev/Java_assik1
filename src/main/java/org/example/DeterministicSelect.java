package org.example;

import java.util.Arrays;

public class DeterministicSelect {


    public static int select(int[] arr, int k) {
        if (k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("k out of bounds");
        }
        return select(arr, 0, arr.length - 1, k);
    }


    private static int select(int[] arr, int left, int right, int k) {
        while (true) {
            if (left == right) return arr[left];


            int pivotIndex = medianOfMedians(arr, left, right);

            int pivotValue = arr[pivotIndex];
            Util.swap(arr, pivotIndex, right);
            int pos = Util.partition(arr, left, right, pivotValue);

            if (k == pos) return arr[k];
            else if (k < pos) right = pos - 1;
            else left = pos + 1;
        }
    }


    private static int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n < 5) {
            Arrays.sort(arr, left, right + 1);
            return left + n / 2;
        }

        int numMedians = (int) Math.ceil((double) n / 5);
        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);

            Arrays.sort(arr, subLeft, subRight + 1);

            int median = subLeft + (subRight - subLeft) / 2;
            Util.swap(arr, left + i, median);
        }


        return medianOfMedians(arr, left, left + numMedians - 1);
    }
}
