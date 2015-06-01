package se751.counters;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public class ProceduralFibonacciCounter {

    public static int count(int value) {
        int count = 0;
        for (int i = 1; i < value + 1; i++) {
            count += FibonacciCounter.fibonacci(i);
        }
        return count;
    }
}
