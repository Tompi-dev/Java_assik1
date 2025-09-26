package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClosestPairTest {

    @Test
    void testSimpleCase() {
        ClosestPair.Point[] pts = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(3, 4),
                new ClosestPair.Point(1, 1)
        };
        double result = ClosestPair.findClosestPair(pts);
        assertEquals(Math.sqrt(2), result, 1e-6);
    }

    @Test
    void testIdenticalPoints() {
        ClosestPair.Point[] pts = {
                new ClosestPair.Point(1, 1),
                new ClosestPair.Point(1, 1),
                new ClosestPair.Point(2, 2)
        };
        double result = ClosestPair.findClosestPair(pts);
        assertEquals(0.0, result, 1e-6);
    }

    @Test
    void testLinePoints() {
        ClosestPair.Point[] pts = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(1, 0),
                new ClosestPair.Point(2, 0),
                new ClosestPair.Point(3, 0)
        };
        double result = ClosestPair.findClosestPair(pts);
        assertEquals(1.0, result, 1e-6);
    }
}
