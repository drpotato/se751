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
    protected void increment() {
        atomicInteger.getAndIncrement();
    }

    @Override
    protected int getCount() {
        return atomicInteger.get();
    }
}
