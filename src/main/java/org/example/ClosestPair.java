package org.example;

import java.util.*;

public class ClosestPair {

    public static class Point {
        public double x, y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static double dist(Point a, Point b) {
        Metrics.incComparisons();
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double findClosestPair(Point[] points) {
        Metrics.incAllocations(); // клон массива
        Point[] sortedByX = points.clone();
        Arrays.sort(sortedByX, Comparator.comparingDouble(p -> p.x));

        Point[] buffer = new Point[points.length];
        Metrics.incAllocations();
        return closest(sortedByX, buffer, 0, points.length - 1);
    }

    private static double closest(Point[] pts, Point[] buf, int left, int right) {
        Metrics.enterRecursion(); // зашли в рекурсию

        if (right - left <= 3) {
            double min = Double.POSITIVE_INFINITY;
            for (int i = left; i <= right; i++) {
                for (int j = i + 1; j <= right; j++) {
                    min = Math.min(min, dist(pts[i], pts[j]));
                }
            }
            Arrays.sort(pts, left, right + 1, Comparator.comparingDouble(p -> p.y));
            Metrics.exitRecursion();
            return min;
        }

        int mid = (left + right) / 2;
        double midX = pts[mid].x;

        double dLeft = closest(pts, buf, left, mid);
        double dRight = closest(pts, buf, mid + 1, right);
        double d = Math.min(dLeft, dRight);

        mergeByY(pts, buf, left, mid, right);

        int stripSize = 0;
        for (int i = left; i <= right; i++) {
            Metrics.incComparisons(); // проверка для strip
            if (Math.abs(pts[i].x - midX) < d) {
                buf[stripSize++] = pts[i];
            }
        }

        for (int i = 0; i < stripSize; i++) {
            for (int j = i + 1; j < stripSize && (buf[j].y - buf[i].y) < d; j++) {
                d = Math.min(d, dist(buf[i], buf[j]));
            }
        }

        Metrics.exitRecursion();
        return d;
    }

    private static void mergeByY(Point[] pts, Point[] buf, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            Metrics.incComparisons();
            if (pts[i].y <= pts[j].y) buf[k++] = pts[i++];
            else buf[k++] = pts[j++];
        }
        while (i <= mid) buf[k++] = pts[i++];
        while (j <= right) buf[k++] = pts[j++];

        for (i = left; i <= right; i++) pts[i] = buf[i];
    }
}
