package org.example;

import java.io.FileWriter;
import java.io.IOException;


public class Metrics {
    private static long comparisons = 0;
    private static long allocations = 0;


    private static int currentDepth = 0;
    private static int maxDepth = 0;


    private static long startTime = 0;
    private static long endTime = 0;


    public static void reset() {
        comparisons = 0;
        allocations = 0;
        currentDepth = 0;
        maxDepth = 0;
        startTime = 0;
        endTime = 0;
    }


    public static void incComparisons() {
        comparisons++;
    }

    public static long getComparisons() {
        return comparisons;
    }


    public static void incAllocations() {
        allocations++;
    }

    public static long getAllocations() {
        return allocations;
    }


    public static void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public static void exitRecursion() {
        currentDepth--;
    }

    public static int getMaxDepth() {
        return maxDepth;
    }

    public static void startTimer() {
        startTime = System.nanoTime();
    }

    public static void stopTimer() {
        endTime = System.nanoTime();
    }

    public static long getElapsedTimeMillis() {
        return (endTime - startTime) / 1_000_000;
    }


    public static void writeToCSV(String filename, String algo, int n) {
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(algo + "," + n + "," + getComparisons() + "," + getAllocations()
                    + "," + getMaxDepth() + "," + getElapsedTimeMillis() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // teper tochno
}
