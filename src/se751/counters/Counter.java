package se751.counters;

import se751.Benchmark;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public abstract class Counter implements Runnable {

    abstract void increment();
    abstract int getCount();

    @Override
    public void run() {
        while (getCount() < Benchmark.WORK_SIZE) {
            increment();
        }
    }
}
