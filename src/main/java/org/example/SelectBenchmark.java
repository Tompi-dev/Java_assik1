package org.example;

import org.example.DeterministicSelect;
import org.openjdk.jmh.annotations.*;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class SelectBenchmark {

    private int[] arr;
    private int k;
    private Random rand = new Random();

    @Param({"1000", "10000", "100000"})
    private int n;

    @Setup(Level.Invocation)
    public void setup() {
        arr = rand.ints(n, 0, 1_000_000).toArray();
        k = n / 2; // median
    }

    @Benchmark
    public int deterministicSelect() {
        return DeterministicSelect.select(arr.clone(), k);
    }

    @Benchmark
    public int arraysSortSelect() {
        int[] copy = arr.clone();
        Arrays.sort(copy);
        return copy[k];
    }
}
