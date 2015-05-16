package se751.counters;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public class AtomicCounter extends Counter {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    void increment() {
        atomicInteger.getAndIncrement();
    }

    @Override
    int getCount() {
        return atomicInteger.get();
    }
}
