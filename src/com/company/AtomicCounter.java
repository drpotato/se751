package com.company;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public class AtomicCounter implements Counter {
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private static final int MAX_VALUE = 100000000;
    @Override
    public void run() {
        while (atomicInteger.get() < MAX_VALUE) {
            atomicInteger.getAndIncrement();
        }
    }

    public int getCount() {
        return atomicInteger.get();
    }
}
