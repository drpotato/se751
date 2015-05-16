package se751.counters;

import se751.Benchmark;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public class AtomicCounter implements Runnable {
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    @Override
    public void run() {
        while (atomicInteger.get() < Benchmark.WORK_SIZE) {
            atomicInteger.getAndIncrement();
        }
    }
}
