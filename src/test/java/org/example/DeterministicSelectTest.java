package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Random;

class DeterministicSelectTest {

    @Test
    void testSmallArray() {
        int[] arr = {3, 1, 2};
        assertEquals(1, DeterministicSelect.select(arr.clone(), 0)); // smallest
        assertEquals(2, DeterministicSelect.select(arr.clone(), 1)); // median
        assertEquals(3, DeterministicSelect.select(arr.clone(), 2)); // largest
    }

    @Test
    void testRandomArrays() {
        Random rand = new Random();
        for (int t = 0; t < 100; t++) {
            int[] arr = rand.ints(20, -50, 50).toArray();
            int k = rand.nextInt(arr.length);

            int expected = Arrays.stream(arr.clone()).sorted().toArray()[k];
            int actual = DeterministicSelect.select(arr, k);
            assertEquals(expected, actual);
        }
    }

    @Test
    void testWithDuplicates() {
        int[] arr = {5, 5, 5, 5, 5};
        assertEquals(5, DeterministicSelect.select(arr, 3));
    }
}
