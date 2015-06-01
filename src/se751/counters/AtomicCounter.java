package se751.counters;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public class AtomicCounter extends Counter {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public AtomicCounter(Integer workload) {
        super(workload);
    }

    @Override
    void increment() {
        atomicInteger.getAndIncrement();
    }

    @Override
    void resetCount() {
        atomicInteger.set(0);
    }

    @Override
    int getCount() {
        return atomicInteger.get();
    }
}
