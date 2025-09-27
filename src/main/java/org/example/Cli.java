package org.example;

import java.util.Random;

public class Cli {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java -jar algo-assignment.jar <algo> <n>");
            System.out.println("Algorithms: mergesort, quicksort, select, closest");
            return;
        }

        String algo = args[0].toLowerCase();
        int n = Integer.parseInt(args[1]);

        int[] arr = new int[n];
        Random rand = new Random();


        for (int i = 0; i < n; i++) arr[i] = rand.nextInt(1000000);

        Metrics.reset();
        Metrics.startTimer();

        switch (algo) {
            case "mergesort":
                MergeSort.sort(arr);
                break;
            case "quicksort":
                QuickSort.sort(arr);
                break;
            case "select":
                int k = n / 2;
                DeterministicSelect.select(arr, k);
                break;
            case "closest":
                ClosestPair.Point[] pts = new ClosestPair.Point[n];
                for (int i = 0; i < n; i++) {
                    pts[i] = new ClosestPair.Point(rand.nextDouble() * 1000, rand.nextDouble() * 1000);
                }
                ClosestPair.findClosestPair(pts);
                break;
            default:
                System.out.println("Unknown algorithm: " + algo);
                return;
        }

        Metrics.stopTimer();
        Metrics.writeToCSV("src/main/results.csv", algo, n);

        System.out.println("Algo: " + algo + " | n=" + n);
        System.out.println("Comparisons: " + Metrics.getComparisons());
        System.out.println("Allocations: " + Metrics.getAllocations());
        System.out.println("Max depth: " + Metrics.getMaxDepth());
        System.out.println("Time (ms): " + Metrics.getElapsedTimeMillis());
    }
}
